package com.bilibili.majiang.demo.service.impl;

import com.bilibili.majiang.demo.Enums.NotificationStatusEnum;
import com.bilibili.majiang.demo.Enums.NotificationTypeEnum;
import com.bilibili.majiang.demo.dto.NotificationDto;
import com.bilibili.majiang.demo.mapper.CommentMapper;
import com.bilibili.majiang.demo.mapper.NotificationMapper;
import com.bilibili.majiang.demo.mapper.QuestionMapper;
import com.bilibili.majiang.demo.mapper.UserMapper;
import com.bilibili.majiang.demo.model.Comment;
import com.bilibili.majiang.demo.model.Notification;
import com.bilibili.majiang.demo.model.Question;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.NotificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ListUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Override
    public PageInfo<NotificationDto> myNotificationpage(Long id, int pn, int pageSize) {
        PageHelper.startPage(pn,pageSize);
        Example example = new Example(Notification.class);
        example.createCriteria().andEqualTo("notifier",id).andEqualTo("status", NotificationStatusEnum.STATUS_UNREAD.getStatus());
        List<Notification> notifications = notificationMapper.selectByExample(example);
        if (ListUtils.isEmpty(notifications)){
            return new PageInfo<>();
        }
        PageInfo<Notification> pageInfo = new PageInfo<>(notifications,5);
        Set<Long> notifierIds = notifications.stream().map(notification -> notification.getNotifier()).collect(Collectors.toSet());
        List<Long> userIdList = new ArrayList<>(notifierIds);
        if (ListUtils.isEmpty(userIdList)){
            return new PageInfo<>();
        }
        Example userExample = new Example(User.class);
        userExample.createCriteria().andIn("id", userIdList);
        List<User> userList = userMapper.selectByExample(userExample);
        Map<Long,String> longUserNameMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), user -> user.getName()));
        Set<Long> commentIds = new HashSet<>();
        Set<Long> questionIds = new HashSet<>();
        for (Notification notification : notifications) {
            if (notification.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()){
                commentIds.add(notification.getOuterid());
            }else{
                questionIds.add(notification.getOuterid());
            }
        }

        List<Long> commentIdList = new ArrayList<>(commentIds);
        Map<Long, Comment> commentMap = new HashMap<>();
        if (commentIdList.size() != 0){
            Example commentExample = new Example(Comment.class);
            commentExample.createCriteria().andIn("id", commentIdList);
            List<Comment> comments = commentMapper.selectByExample(commentExample);
             commentMap.putAll(comments.stream().collect(Collectors.toMap(comment -> comment.getId(), comment -> comment)));
        }

        List<Long> questionIdList = new ArrayList<>(questionIds);
        Map<Long,String> questionMap = new HashMap<>();
        if (questionIdList.size() != 0){
            Example questionExample = new Example(Question.class);
            questionExample.createCriteria().andIn("id", questionIdList);
            List<Question> questions = questionMapper.selectByExample(questionExample);
            if (questions != null){
                questionMap.putAll(questions.stream().collect(Collectors.toMap(question -> question.getId(), question -> question.getDescription())));
            }
        }
        if (notifications !=null && notifications.size() != 0){
            List<NotificationDto> notificationDtoList = notifications.stream().map(notification -> {
                NotificationDto n = new NotificationDto();
                n.setId(notification.getId());
                n.setNotifier(notification.getNotifier());
                n.setType(NotificationTypeEnum.typeOfName(notification.getType()));
                n.setNotifierName(longUserNameMap.get(notification.getNotifier()));
                n.setStatus(notification.getStatus());
                if (commentMap.size() != 0 && notification.getType() == NotificationTypeEnum.REPLY_COMMENT.getType()) {
                    n.setOuterid(commentMap.get(notification.getOuterid()).getParentId());
                    n.setOuterTitle(commentMap.get(notification.getOuterid()).getContent());
                }
                if (questionMap.size() != 0 && notification.getType() == NotificationTypeEnum.REPLY_QUESTION.getType()) {
                    n.setOuterTitle(questionMap.get(notification.getOuterid()));
                    n.setOuterid(notification.getOuterid());
                }
                return n;
            }).collect(Collectors.toList());
//            PageInfo<NotificationDto> pageInfo = new PageInfo<>(notificationDtoList,5);
            PageInfo<NotificationDto> pageInfo1 = new PageInfo<>();
            BeanUtils.copyProperties(pageInfo,pageInfo1);
            pageInfo1.setList(notificationDtoList);
            return pageInfo1;
        }

        return null;

    }

    @Override
    public Notification read(Long id) {
        Example e = new Example(Notification.class);
        e.createCriteria().andEqualTo("id",id);
        Notification notification = notificationMapper.selectOneByExample(e);
        notification.setStatus(NotificationStatusEnum.STATUS_READ.getStatus());
        int i = notificationMapper.updateByPrimaryKeySelective(notification);
        return notification;
    }
    @Override
    public Integer unReadCount(Long id){
        Example example = new Example(Notification.class);
        example.createCriteria().andEqualTo("status",NotificationStatusEnum.STATUS_UNREAD.getStatus()).andEqualTo("receiver",id);
        return notificationMapper.selectCountByExample(example);
    }
}

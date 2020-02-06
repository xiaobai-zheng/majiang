package com.bilibili.majiang.demo.service.impl;

import com.bilibili.majiang.demo.Enums.CustomTypeEnum;
import com.bilibili.majiang.demo.dto.CommentDto;
import com.bilibili.majiang.demo.exception.CustomExceptionCodeImpl;
import com.bilibili.majiang.demo.mapper.CommentMapper;
import com.bilibili.majiang.demo.mapper.QuestionMapper;
import com.bilibili.majiang.demo.mapper.UserMapper;
import com.bilibili.majiang.demo.model.Comment;
import com.bilibili.majiang.demo.model.Question;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.CommentService;
import com.bilibili.majiang.demo.vo.Msg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public Msg submitComment(Comment comment) {
        int num  = 0;
        if(comment.getParentId()==null || comment.getParentId() == 0){
            return Msg.fail(CustomExceptionCodeImpl.FIND_PARENTID_EXCEPTION);
        }
        if (comment.getType() == null || !CustomTypeEnum.isExit(comment.getType())) {
            return Msg.fail(CustomExceptionCodeImpl.FIND_TYPE_EXCEPTION);
        }
        if (comment.getType() == CustomTypeEnum.QUESTION_TYPE.getType()){
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null){
                return Msg.fail(CustomExceptionCodeImpl.FIND_QUESTION_EXCEPTION);
            }
            num = commentMapper.insertSelective(comment);
        }else {
            Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (comment1 == null){
                return Msg.fail(CustomExceptionCodeImpl.FIND_COMMENT_EXCEPTION);
            }
            num = commentMapper.insertSelective(comment);
        }
        if (num <=0){
            return Msg.fail();
        }else {
            return Msg.success();
        }
    }

    @Override
    public List<CommentDto> getComment(Long parentId,int type) {
        Example example = new Example(Comment.class);
        example.createCriteria().andEqualTo("parentId",parentId).andEqualTo("type",type);
        example.setOrderByClause("gem_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        if (ListUtils.isEmpty(comments)){
            return null;
        }
        Set<Long> commentator = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentator);
        Example userExample = new Example(User.class);
        userExample.createCriteria().andIn("id",userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        List<CommentDto> commentDtos = comments.stream().map(comment -> {
            User user = userMap.get(comment.getCommentator());
            CommentDto commentDto = new CommentDto();
            commentDto.setAvatarUrl(user.getAvatarUrl());
            commentDto.setName(user.getName());
            BeanUtils.copyProperties(comment, commentDto);
            return commentDto;
        }).collect(Collectors.toList());
        return commentDtos;
    }
}

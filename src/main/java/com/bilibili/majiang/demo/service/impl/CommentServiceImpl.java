package com.bilibili.majiang.demo.service.impl;

import com.bilibili.majiang.demo.Enums.CustomTypeEnum;
import com.bilibili.majiang.demo.exception.CustomExceptionCodeImpl;
import com.bilibili.majiang.demo.mapper.CommentMapper;
import com.bilibili.majiang.demo.mapper.QuestionMapper;
import com.bilibili.majiang.demo.model.Comment;
import com.bilibili.majiang.demo.model.Question;
import com.bilibili.majiang.demo.service.CommentService;
import com.bilibili.majiang.demo.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
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
            questionMapper.incCommentCount(comment.getParentId());
        }
        if (num <=0){
            return Msg.fail();
        }else {
            return Msg.success();
        }
    }
}

package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.dto.CommentDto;
import com.bilibili.majiang.demo.model.Comment;
import com.bilibili.majiang.demo.model.User;
import com.bilibili.majiang.demo.service.CommentService;
import com.bilibili.majiang.demo.vo.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/submitComment")
    @ResponseBody
    public Msg submitComment(@RequestBody CommentDto commentDto, HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGemCreate(System.currentTimeMillis());
        comment.setGemModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentator(user.getId());
        Msg msg = commentService.submitComment(comment);
        System.out.println(msg);
        return msg;
    }
}

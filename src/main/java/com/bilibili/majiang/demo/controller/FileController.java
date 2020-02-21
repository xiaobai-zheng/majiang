package com.bilibili.majiang.demo.controller;

import com.bilibili.majiang.demo.dto.FileDto;
import com.bilibili.majiang.demo.exception.CustomException;
import com.bilibili.majiang.demo.exception.CustomExceptionCodeImpl;
import com.bilibili.majiang.demo.provider.UcloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private UcloudProvider ucloudProvider;
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDto upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multipartHttpServletRequest.getFile("editormd-image-file");
        try {
            String fileUrl= ucloudProvider.ucloud(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
            FileDto fileDto = new FileDto();
            fileDto.setSuccess(1);
            fileDto.setUrl(fileUrl);
            return fileDto;
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(CustomExceptionCodeImpl.UCLOUD_FAIL);
        }
    }
}

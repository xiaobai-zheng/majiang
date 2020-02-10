package com.bilibili.majiang.demo.cache;

import com.bilibili.majiang.demo.dto.TagDto;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDto> getTagDtos(){
        List<TagDto> tagDtos = new ArrayList<>();
        TagDto leadingEnd  = new TagDto();
        leadingEnd.setCategoryName("前端");
        List<String>  leadTags = Arrays.asList("javascript", "前端", "vue.js", "css", "html", "html5", "node.js", "react.js", "jquery", "css3", "es6", "typescript", "chrome", "npm", "bootstrap", "sass", "less", "chrome-devtools", "firefox", "angular", "coffeescript", "safari", "postcss", "postman", "fiddler", "webkit", "yarn", "firebug", "edge");
        leadingEnd.setTags(leadTags);
        tagDtos.add(leadingEnd);

        TagDto afterEnd = new TagDto();
        afterEnd.setCategoryName("后端");
        List<String> afterTags = Arrays.asList("php","java","node.js","python","c++","c","golang","spring","django","springboot","flask","后端","c#","swoole","ruby","asp.net","ruby-on-rails","scala","rust","lavarel","爬虫");
        afterEnd.setTags(afterTags);
        tagDtos.add(afterEnd);

        TagDto db = new TagDto();
        db.setCategoryName("数据库");
        List<String> dbTags = Arrays.asList("mysql","redis","mongodb","sql","数据库","json","elasticsearch","nosql","memcached","postgresql","sqlite","mariadb");
        db.setTags(dbTags);
        tagDtos.add(db);

        TagDto al = new TagDto();
        al.setCategoryName("人工智能");
        List<String> alTags = Arrays.asList("算法机器学习", "人工智能", "深度学习", "数据挖掘", "tensorflow", "神经网络", "图像识别", "人脸识别", "自然语言", "处理机器人", "pytorch", "自动驾驶");
        al.setTags(alTags);
        tagDtos.add(al);

        TagDto tool = new TagDto();
        tool.setCategoryName("工具");
        List<String> toolTags = Arrays.asList("git", "github", "macos", "visual-studio-code", "windows", "vim", "sublime-text", "intellij-idea", "eclipse", "phpstorm", "webstorm", "编辑器", "svn", "visual-studio", "pycharm", "emacs");
        tool.setTags(toolTags);
        tagDtos.add(tool);
        return tagDtos;
    }
    public static List<String> filterInvalid(String tagStr){
        String[] inputTags = StringUtils.split(tagStr, ",");
        List<TagDto> tagDtos = getTagDtos();
        List<String> tags = tagDtos.stream().flatMap(tagDto -> tagDto.getTags().stream()).collect(Collectors.toList());
        List<String> invalidTags = Arrays.stream(inputTags).filter(t -> !tagDtos.contains(t)).collect(Collectors.toList());
        if (invalidTags.size() != 0){
            return invalidTags;
        }
        return new ArrayList<>();
    }
}

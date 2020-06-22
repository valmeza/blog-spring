package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/post")
    @ResponseBody
    public String post() {
        return "Post";
    }

    @GetMapping("/post/{id}")
    @ResponseBody
    public String postId(@PathVariable long id) {
        return "Post Id: " + id;
    }

    @GetMapping("/post/create")
    @ResponseBody
    public String postCreate() {
        return "Create Post";
    }

    @PostMapping("/post/create")
    @ResponseBody
    public String postPosted() {
        return "Posted";
    }
}

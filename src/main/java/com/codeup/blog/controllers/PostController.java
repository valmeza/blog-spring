package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/post")
    public String post(Model model) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("Post 1", "Description 1"));
        posts.add(new Post("Post 2", "Description 2"));
        posts.add(new Post("Post 3", "Description 3"));
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/post/show/{id}")
    public String postId(@PathVariable long id, Model model) {
        Post post = new Post("Post", "Description");
        model.addAttribute("p", post);
        return "posts/show";
    }

    @GetMapping("/post/create")
    @ResponseBody
    public String postCreate() {
        return "view the form for creating a post";
    }

    @PostMapping("/post/create")
    @ResponseBody
    public String postPosted() {
        return "create a new post";
    }
}

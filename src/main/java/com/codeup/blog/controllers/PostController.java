package com.codeup.blog.controllers;

import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // dependency injection
    private PostsRepository postsDao;
    public PostController(PostsRepository postsRepository) {
        postsDao = postsRepository;
    }

    @GetMapping("/post")
    public String index(Model model) {
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/post/show/{id}")
    public String show(@PathVariable long id, Model model) {
        Post post = postsDao.getOne(id);
        model.addAttribute("pId", id);
        model.addAttribute("p", post);
        return "posts/show";
    }

    @GetMapping("/post/create")
    @ResponseBody
    public String showForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/post/create")
    @ResponseBody
    public String save() {
        Post newPost = new Post("My first Blog", "My first Blog post!!");
        postsDao.save(newPost);
        return "Create a new post";
    }

    // this finds one and displays it in the form we want to edit
    @GetMapping("posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable long id) {
        // find an post
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("posts/{id}/edit")
    @ResponseBody
    public String update(@PathVariable long id,
                         @RequestParam(name="title") String title,
                         @RequestParam(name="description") String description) {
        // find a post
        Post found = postsDao.getOne(id); // select * from ads where id = ?
        // edit the add
        found.setTitle(title);
        found.setBody(description);
        // save the changes
        postsDao.save(found);
        return "Post Updated!";
    }

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String destroy(@PathVariable long id) {
        postsDao.deleteById(id);
        return "Post Deleted!";
    }

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam(name="search") String search) {
        List<Post> posts = postsDao.searchByTitle(search);
        model.addAttribute("posts", posts);
        return "posts/index";
    }
}

package com.codeup.blog.controllers;

import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.daos.UsersRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.services.EmailService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // dependency injection
    private PostsRepository postsDao;
    private UsersRepository usersDao;
    private final EmailService emailService;

    public PostController(PostsRepository postsRepository, UsersRepository usersRepository, EmailService emailService) {
        this.postsDao = postsRepository;
        this.usersDao = usersRepository;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/show/{id}")
    public String show(@PathVariable long id, Model model) {
        Post post = postsDao.getOne(id);
        model.addAttribute("pId", id);
        model.addAttribute("p", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showForm(Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String save(@ModelAttribute Post savePost) {
        User currentUser = usersDao.getOne(1L);
        savePost.setOwner(currentUser);
        Post postInDb = postsDao.save(savePost);
        emailService.prepareAndSend(savePost, "A new post has been created!", "An post has been created with the id of " + currentUser);
        return "redirect:/posts/show/" + postInDb.getId();
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
    public String update(@ModelAttribute Post editPost) {
        // save the changes
        User currentUser = usersDao.getOne(1L);
        editPost.setOwner(currentUser);
        postsDao.save(editPost);
        return "redirect:/posts/show/" + editPost.getId();
    }

    @PostMapping("/posts/{id}/delete")
    public String destroy(@ModelAttribute Post deletePost) {
        User currentUser = usersDao.getOne(1L);
        deletePost.setOwner(currentUser);
        postsDao.delete(deletePost);
        return "redirect:/posts";
    }

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam(name="search") String search) {
        List<Post> posts = postsDao.searchByTitle(search);
        model.addAttribute("posts", posts);
        return "posts/index";
    }
}

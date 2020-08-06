package com.codeup.blog.controllers;

import com.codeup.blog.daos.UsersRepository;
import com.codeup.blog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UsersRepository users;
    @Autowired
    private PasswordEncoder passwordEncoder;


//    public UserController(UsersRepository users, PasswordEncoder passwordEncoder) {
//        this.users = users;
//        this.passwordEncoder = passwordEncoder;
//    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@Validated User user, Errors validation, Model model){
        String username = user.getUsername();
        String email = user.getEmail();
        User userExists = users.findByUsername(username);
        User emailExists = users.findByEmail(email);

        if(userExists != null) {
            validation.rejectValue("username", "user.username", username + " already exists");
        }

        if(emailExists != null) {
            validation.rejectValue("email", "user.email", email + " already exists");
        }

        if(validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "users/sign-up";
        }

        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }
}

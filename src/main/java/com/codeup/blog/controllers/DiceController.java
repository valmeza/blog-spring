package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String dice() {

        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceNumber(@PathVariable int n, Model model) {
        int min = 1;
        int max = 6;
        int random = (int) (Math.random() * (max - min + 1)) + min;
        model.addAttribute("n", n);
        model.addAttribute("random", random);
        model.addAttribute("guessed", random == n);
        return "roll-dice";
    }
}

package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{number}/and/{number2}")
    @ResponseBody
    public int add(@PathVariable int number, @PathVariable int number2) {
        return number + number2;
    }

    @GetMapping("/subtract/{number}/from/{number2}")
    @ResponseBody
    public int sub(@PathVariable int number, @PathVariable int number2) {
        return number2 - number;
    }

    @GetMapping("/multiply/{number}/and/{number2}")
    @ResponseBody
    public int multiply(@PathVariable int number, @PathVariable int number2) {
        return number * number2;
    }

    @GetMapping("/divide/{number}/by/{number2}")
    @ResponseBody
    public int divide(@PathVariable int number, @PathVariable int number2) {
        return number / number2;
    }
}

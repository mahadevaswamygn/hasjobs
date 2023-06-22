package com.example.hasjobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class courseController {

    @GetMapping(value = "/java")
    public String java() {
        return "java";
    }

    @GetMapping(value = "spring-boot")
    public String springBoot() {
        return "spring-boot";
    }
}

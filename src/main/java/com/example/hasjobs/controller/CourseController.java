package com.example.hasjobs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CourseController {

    @GetMapping(value = "/java")
    public String java() {
        return "java";
    }

    @GetMapping(value = "spring-boot")
    public String springBoot() {
        return "spring-boot";
    }


    @GetMapping(value = "/course")
    public String takeCourse(Principal principal){
        return "course-details";
    }

    @GetMapping(value = "/java-course-details")
    public String javaCourse(Principal principal){
        return "java-course-page";
    }
    @GetMapping(value = "/spring-course")
    public String springCourse(){
        return "spring-course-page";
    }
}

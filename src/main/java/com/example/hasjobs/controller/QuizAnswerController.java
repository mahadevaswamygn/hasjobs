package com.example.hasjobs.controller;

import com.example.hasjobs.entity.QuizAnswer;
import com.example.hasjobs.service.QuizAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;

@Controller
public class QuizAnswerController {
    private final QuizAnswerService quizAnswerService;

    @Autowired
    public QuizAnswerController(QuizAnswerService quizAnswerService) {
        this.quizAnswerService = quizAnswerService;
    }

    @GetMapping("/quiz")
    public String showQuizPage(@ModelAttribute("quizAnswer") QuizAnswer quizAnswer) {
        return "java_question";
    }

    @GetMapping("/spring/quiz")
    public String showSpringQuizPage(@ModelAttribute("quizAnswer") QuizAnswer quizAnswer) {
        return "spring_question";
    }

    @PostMapping("/spring/quiz/submit")
    public String submitSpringQuiz(@ModelAttribute("quizAnswer") QuizAnswer quizAnswer, Model model, Principal principal) {
        quizAnswerService.saveSpringQuizAnswer(quizAnswer);
        int score = quizAnswer.getScore();
        model.addAttribute("name",principal.getName());
        model.addAttribute("score",score);
        return "success";
    }

    @GetMapping("spring-quiz")
    public String springQuiz(@ModelAttribute("quizAnswer") QuizAnswer quizAnswer) {
        return "spring-quiz-page";
    }


}

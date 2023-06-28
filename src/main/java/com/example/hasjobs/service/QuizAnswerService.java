package com.example.hasjobs.service;

import com.example.hasjobs.entity.QuizAnswer;
import com.example.hasjobs.repository.QuizAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizAnswerService {
    private final QuizAnswerRepository quizAnswerRepository;

    @Autowired
    public QuizAnswerService(QuizAnswerRepository quizAnswerRepository) {
        this.quizAnswerRepository = quizAnswerRepository;
    }

    public void saveQuizAnswer(QuizAnswer quizAnswer) {
        int score = calculateScore(quizAnswer);
        quizAnswer.setScore(score);
        quizAnswerRepository.save(quizAnswer);
    }

    public void saveSpringQuizAnswer(QuizAnswer quizAnswer) {
        int score = springCalculateScore(quizAnswer);
        quizAnswer.setScore(score);
        quizAnswerRepository.save(quizAnswer);
    }

    private int calculateScore(QuizAnswer quizAnswer) {
        int score = 0;
        if ("a".equals(quizAnswer.getQ1())) {
            score++;
        }
        if ("d".equals(quizAnswer.getQ2())) {
            score++;
        }
        if ("d".equals(quizAnswer.getQ3())) {
            score++;
        }
        if ("c".equals(quizAnswer.getQ4())) {
            score++;
        }
        if ("d".equals(quizAnswer.getQ5())) {
            score++;
        }

        return score;
    }

    private int springCalculateScore(QuizAnswer quizAnswer) {
        int score = 0;
        if ("d".equals(quizAnswer.getQ1())) {
            score++;
        }
        if ("b".equals(quizAnswer.getQ2())) {
            score++;
        }
        if ("d".equals(quizAnswer.getQ3())) {
            score++;
        }
        if ("c".equals(quizAnswer.getQ4())) {
            score++;
        }
        if ("a".equals(quizAnswer.getQ5())) {
            score++;
        }

        return score;
    }
}

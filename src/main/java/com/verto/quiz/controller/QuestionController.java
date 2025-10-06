package com.verto.quiz.controller;


import com.verto.quiz.dto.QuestionDto;
import com.verto.quiz.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz/question")
public class QuestionController {

    @Autowired
    private QuizService questionService;


    //Add Single Question
    @PostMapping("/{quizId}")
    public ResponseEntity<QuestionDto> addSingleQuestion(
            @PathVariable("quizId") String quizId,
            @Valid @RequestBody QuestionDto questionDto
    ){
        QuestionDto addedQuestionDto = questionService.addQuestionToQuiz(quizId, questionDto);

        return new ResponseEntity<>(addedQuestionDto, HttpStatus.CREATED);
    }


    //Add Multiple Questions in Quiz
    @PostMapping("/multiple/{quizId}")
    public ResponseEntity<List<QuestionDto>> addMultipleQuestions(
            @PathVariable("quizId") String quizId,
            @Valid @RequestBody List<QuestionDto> questionDtoList
    ){
        List<QuestionDto> questionDtoResList = questionService.addMultipleQuestions(quizId, questionDtoList);

        return ResponseEntity.status(HttpStatus.CREATED).body(questionDtoResList);
    }


}

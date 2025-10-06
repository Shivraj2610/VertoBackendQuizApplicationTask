package com.verto.quiz.controller;


import com.verto.quiz.custom.ApiResponseMessage;
import com.verto.quiz.custom.PageableResponse;
import com.verto.quiz.dto.QuestionDto;
import com.verto.quiz.dto.QuizDto;
import com.verto.quiz.dto.QuizScoreDto;
import com.verto.quiz.dto.QuizSubmissionDto;
import com.verto.quiz.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    //Create Quiz Controller
    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(
            @Valid @RequestBody QuizDto quizDto
    ){
        QuizDto createdQuizDto = quizService.createQuiz(quizDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuizDto);
    }


    //Get Single Quiz Controller
    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDto> getSingleQuiz(@PathVariable("quizId") String quizId){
        QuizDto singleQuiz = quizService.getSingleQuiz(quizId);

        return new ResponseEntity<>(singleQuiz, HttpStatus.OK);
    }


    //Get All Quiz Controller
    @GetMapping
    public ResponseEntity<PageableResponse<QuizDto>> getAllQuiz(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        PageableResponse<QuizDto> quizDtoPageableResponse = quizService.allQuiz(pageNumber, pageSize, sortBy, sortDir);

        return ResponseEntity.status(HttpStatus.OK).body(quizDtoPageableResponse);
    }


    //Get the Questions from Quiz
    @GetMapping("/questions/{quizId}")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable String quizId){
        List<QuestionDto> quizQuestions = quizService.fetchQuizQuestion(quizId);

        return ResponseEntity.ok(quizQuestions);
    }


    //Submit the Quiz and get the Score
    @GetMapping("/score")
    public ResponseEntity<QuizScoreDto> submitAndGetScore(
            @RequestBody QuizSubmissionDto quizSubmissionDto
    ){
        QuizScoreDto quizScoreDto = quizService.submitAndGetScore(quizSubmissionDto);

        return new ResponseEntity<>(quizScoreDto, HttpStatus.OK);
    }


    //Remove the Question from Quiz
    @DeleteMapping("/{quizId}/{questionId}")
    public ResponseEntity<ApiResponseMessage> removeQuestion(
            @PathVariable String quizId,
            @PathVariable String questionId
    ){
        quizService.removeQuizQuestion(quizId, questionId);

        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message("Question Deleted Successfully")
                .status(HttpStatus.OK)
                .success(true)
                .build();

        return ResponseEntity.ok(responseMessage);
    }


    //Remove Quiz
    @DeleteMapping("/{quizId}")
    public ResponseEntity<ApiResponseMessage> removeQuiz(@PathVariable String quizId){
        quizService.removeQuiz(quizId);

        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message("Quiz deleted Successfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }



    //Update the Question
    @PutMapping("/{quizId}/{questionId}")
    public ResponseEntity<QuestionDto> updateQuizQuestion(
            @PathVariable String quizId,
            @PathVariable String questionId,
            @RequestBody QuestionDto questionDto
    ){

        QuestionDto updatedQuestion = quizService.updateQuestion(quizId, questionId, questionDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedQuestion);
    }


    //Update Quiz
    @PutMapping("/{quizId}")
    public ResponseEntity<QuizDto> updateQuiz(
            @PathVariable String quizId,
            @RequestBody QuizDto quizDto
    ){
        QuizDto updatedQuizDto = quizService.updateQuiz(quizId, quizDto);

        return new ResponseEntity<>(updatedQuizDto, HttpStatus.OK);
    }
}

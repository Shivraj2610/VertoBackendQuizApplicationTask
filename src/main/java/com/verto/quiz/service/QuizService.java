package com.verto.quiz.service;


import com.verto.quiz.custom.PageableResponse;
import com.verto.quiz.dto.QuestionDto;
import com.verto.quiz.dto.QuizDto;
import com.verto.quiz.dto.QuizScoreDto;
import com.verto.quiz.dto.QuizSubmissionDto;

import java.util.List;

public interface QuizService {

    //Create New Quiz
    QuizDto createQuiz(QuizDto quizDto);

    //Get Single Quiz by quizId
    QuizDto getSingleQuiz(String quizId);


    //Fetch All Quiz
    PageableResponse<QuizDto> allQuiz(int pageNumber, int pageSize, String sortBy, String sortDir);


    //Fetch All Question from Specific Quiz
    public List<QuestionDto> fetchQuizQuestion(String quizId);


    //Remove A Quiz Question
    public void removeQuizQuestion(String quizId, String questionId);

    //Remove Quiz
    public void removeQuiz(String quizId);


    //Update Quiz
    public QuizDto updateQuiz(String quizId,QuizDto quizDto);


    //Update Question in Quiz
    public QuestionDto updateQuestion(String quizId, String questionId, QuestionDto questionDto);

    //Add Single Question and it's Options into Quiz
    QuestionDto addQuestionToQuiz(String quizId, QuestionDto questionDto);

    //Add Multiple Questions into Quiz
    public List<QuestionDto> addMultipleQuestions(String quizId, List<QuestionDto> multipleQuestionDto);


    //Submit the
    public QuizScoreDto submitAndGetScore(QuizSubmissionDto quizSubmissionDto);
}

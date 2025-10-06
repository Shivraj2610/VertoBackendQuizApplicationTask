package com.verto.quiz.service;


import com.verto.quiz.dto.QuizScoreDto;
import com.verto.quiz.dto.QuizSubmissionDto;
import com.verto.quiz.entity.Option;
import com.verto.quiz.entity.Question;
import com.verto.quiz.entity.Quiz;
import com.verto.quiz.repository.QuizRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.*;

@SpringBootTest
public class SubmitQuizTest {

    @MockitoBean
    private QuizRepository quizRepository;


    @Autowired
    private QuizService quizService;

    Map<String, Integer> answer=new HashMap<>();

    Quiz quiz;

    @BeforeEach
    public void init(){

        List<Option> optionsOfQuestion = new ArrayList<>();
        Option option1 = Option.builder()
                .optionText("Rahul Gandi")
                .optionId(1)
                .correct(false)
                .build();

        Option option2 = Option.builder()
                .optionText("Modi")
                .optionId(2)
                .correct(true)
                .build();
        optionsOfQuestion.add(option1);
        optionsOfQuestion.add(option2);

        Question question = Question.builder()
                .questionId("que1")
                .questionText("Who is Prime minister of India")
                .options(optionsOfQuestion)
                .correctOption(option2)
                .build();


        List<Option> optionsOfQuestion1 = new ArrayList<>();
        option1 = Option.builder()
                .optionText("Delhi")
                .optionId(1)
                .correct(false)
                .build();

        option2 = Option.builder()
                .optionText("Mumbai")
                .optionId(2)
                .correct(true)
                .build();
        optionsOfQuestion.add(option1);
        optionsOfQuestion.add(option2);

        Question question1 = Question.builder()
                .questionId("que2")
                .questionText("What is Capital of India")
                .options(optionsOfQuestion)
                .correctOption(option1)
                .build();


        List<Question> questionList=new ArrayList<>();
        questionList.add(question);
        questionList.add(question1);


        quiz = Quiz.builder()
                .title("GK Quiz")
                .questions(questionList)
                .build();

        answer.put(question.getQuestionId(), 2);
        answer.put(question1.getQuestionId(), 1);
    }


    @Test
    public void submitAndGetScoreTest(){

        String quizId="quizId123";

        QuizSubmissionDto quizForSubmit = QuizSubmissionDto.builder()
                .quizId(quizId)
                .answers(answer)
                .build();


        Mockito.when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

        QuizScoreDto quizScoreDto = quizService.submitAndGetScore(quizForSubmit);

        Assertions.assertEquals(2, quizScoreDto.getScore());
    }
}

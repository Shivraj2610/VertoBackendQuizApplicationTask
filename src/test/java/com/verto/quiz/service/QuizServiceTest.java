package com.verto.quiz.service;


import com.verto.quiz.dto.QuestionDto;
import com.verto.quiz.dto.QuizDto;
import com.verto.quiz.entity.Question;
import com.verto.quiz.entity.Quiz;
import com.verto.quiz.repository.QuizRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class QuizServiceTest {

    @Autowired
    private ModelMapper mapper;

    @MockitoBean
    private QuizRepository quizRepository;

    @Autowired
    private QuizService quizService;


    Quiz quiz;
    Quiz quiz1;
    String quizId="quizId1234";
    @BeforeEach
    public void init(){
        Question question = Question.builder()
                .questionText("How many balls in a over")
                .build();

        List<Question> questions=new ArrayList<>();
        questions.add(question);

        quiz = Quiz.builder()
                .title("Mad Quiz")
                .questions(questions)
                .build();

        quiz1 = Quiz.builder()
                .title("Cricket Quiz")
                .build();
    }


    //Test Create User Service
    @Test
    public void createQuizTest(){

        Mockito.when(quizRepository.save(Mockito.any())).thenReturn(quiz);

        QuizDto quizDto = quizService.createQuiz(mapper.map(quiz, QuizDto.class));

        System.out.println(quizDto.getTitle());

        Assertions.assertNotNull(quizDto);
    }



    //Test Get Single Quiz Service using quizId
    @Test
    public void getSingleQuizTest(){


        Mockito.when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

        QuizDto quizDto = quizService.getSingleQuiz(quizId);

        System.out.println(quizDto.getTitle()+quizDto.getQuizId());

        Assertions.assertNotNull(quizDto);
    }



    //Test for Fetch all Quiz Questions
    @Test
    public void fetchQuizQuestionTest(){

        List<Quiz> quizList=new ArrayList<>();
        quizList.add(quiz);
        quizList.add(quiz1);

        Mockito.when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));

        List<QuestionDto> questionDto = quizService.fetchQuizQuestion(quizId);

        Assertions.assertNotNull(questionDto);
    }
}

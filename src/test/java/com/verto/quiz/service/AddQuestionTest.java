package com.verto.quiz.service;

import com.verto.quiz.dto.QuestionDto;
import com.verto.quiz.entity.Option;
import com.verto.quiz.entity.Question;
import com.verto.quiz.entity.Quiz;
import com.verto.quiz.repository.QuestionRepository;
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
public class AddQuestionTest {

    @Autowired
    private ModelMapper mapper;

    @MockitoBean
    private QuizRepository quizRepository;

    @MockitoBean
    private QuestionRepository questionRepository;

    @Autowired
    private QuizService quizService;


    private String quizId;

    Quiz quiz;

    Question question;

    @BeforeEach
    public void init(){
        quizId="quizId123";

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

        question = Question.builder()
                .questionId("que1")
                .questionText("Who is Prime minister of India")
                .options(optionsOfQuestion)
                .build();

        quiz = Quiz.builder()
                .title("GK Quiz")
                .questions(new ArrayList<>())
                .build();

    }



    @Test
    public void createQuestionTest(){

        Mockito.when(quizRepository.findById(quizId)).thenReturn(Optional.of(quiz));
        Mockito.when(questionRepository.save(Mockito.any())).thenReturn(question);

        Mockito.when(quizRepository.save(Mockito.any())).thenReturn(quiz);

        QuestionDto questionDto = quizService.addQuestionToQuiz(quizId, mapper.map(question, QuestionDto.class));

        System.out.println(questionDto.getQuestionId()+" "+questionDto.getQuestionText()+" "+questionDto.getOptions());
        Assertions.assertNotNull(questionDto);
        Assertions.assertEquals("Who is Prime minister of India", questionDto.getQuestionText());
    }
}

package com.verto.quiz.dto;

import com.verto.quiz.entity.Question;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuizDto {

    private String quizId;

    @NotBlank
    private String title;

    private List<QuestionDto> questions;

    private LocalDate createdDate;

}

package com.verto.quiz.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuizScoreDto {

    private int score;

    private int totalQuestions;
}

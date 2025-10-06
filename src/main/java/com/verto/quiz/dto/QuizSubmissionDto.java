package com.verto.quiz.dto;


import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuizSubmissionDto {

    private String quizId;
    private Map<String, Integer> answers;
}

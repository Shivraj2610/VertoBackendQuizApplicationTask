package com.verto.quiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.verto.quiz.entity.Option;
import com.verto.quiz.entity.Quiz;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuestionDto {

    private String questionId;

    @NotNull(message = "Question Text required")
    @Size(max = 300, message = "Less than 300 Characters only")
    private String questionText;

    @NotNull
    private List<OptionDto> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int correctOptionId;

}

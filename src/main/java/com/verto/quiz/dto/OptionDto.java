package com.verto.quiz.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.verto.quiz.entity.Question;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OptionDto {

    private int optionId;

    @NotNull
    private String optionText;

    //The correct answer will not include in API response
    //i.e. I use @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean correct;

//    private QuestionDto question;

}

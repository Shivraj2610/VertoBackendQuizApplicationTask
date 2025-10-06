package com.verto.quiz.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "option_tb")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionId;

    private String optionText;

    @ManyToOne
    private Question question;

    private boolean correct;

    public Option(String optionText, Question question, boolean correct){
        this.optionText=optionText;
        this.question=question;
        this.correct=correct;
    }
}

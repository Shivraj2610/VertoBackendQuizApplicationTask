package com.verto.quiz.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "question_tb")
public class Question {

    @Id
    private String questionId;

    @Column(length = 300)
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Option> options;

    @OneToOne
    @JoinColumn(name = "correct_option_id")
    private Option correctOption;

}

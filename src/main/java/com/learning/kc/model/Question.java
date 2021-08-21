package com.learning.kc.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Question")
public class Question {

    @Id
    @GeneratedValue
    private Long questionId;

    @NotNull
    @NotBlank
    private String question;

    @NotNull
    @NotBlank
    private String answer;

    @Column(columnDefinition="int default 2")
    private int level = 1;

    @NotNull
    @NotBlank
    private String choice1;

    @NotNull
    @NotBlank
    private String choice2;

    @NotNull
    @NotBlank
    private String choice3;

    @NotNull
    @NotBlank
    private String choice4;

    @Column(columnDefinition="varchar(255) default 'General'")
    private String topic = "General";

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

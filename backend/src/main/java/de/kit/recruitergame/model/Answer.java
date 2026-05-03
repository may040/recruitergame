package de.kit.recruitergame.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private boolean isCorrect;

    @ManyToOne
    private Question question;

    private boolean recruiterAnswer;

    public Answer() {}

    public Answer(String text, Question question, boolean
                   isCorrect) {
        this.text = text;
        this.question = question;
        this.isCorrect=isCorrect;
    }

    public boolean isRecruiterAnswer() {
        return recruiterAnswer;
    }

    public void setRecruiterAnswer(boolean recruiterAnswer) {
        this.recruiterAnswer = recruiterAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

}

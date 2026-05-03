package de.kit.recruitergame.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
public class Question {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @ManyToMany(mappedBy = "questions")
    private Set<Recruiter> recruiters;

    private int point;

    public Question() {
    }

    public Question(String text, int point) {
        this.text = text;
        this.point=point;
    }

    public Set<Recruiter> getRecruiter() {
        return recruiters;
    }

    public void setRecruiter(Set<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    public Long getId() {
        return id;
    }


}

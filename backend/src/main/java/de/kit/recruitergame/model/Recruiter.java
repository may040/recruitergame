package de.kit.recruitergame.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recruiters")
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private  String company;

    private int achievedPoints;

    @ManyToMany
    @JoinTable(
            name = "recruiter_question",
            joinColumns = @JoinColumn(name = "recruiter_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions;

    public Recruiter( String name, String company) {
        this.name = name;
        this.company = company;
        this.achievedPoints=0;
        this.questions = new HashSet<>();
    }

    public Recruiter(){

    }

    public int getAchievedPoints() {
        return achievedPoints;
    }

    public void setAchievedPoints(int achievedPoints) {
        this.achievedPoints = achievedPoints;
    }

    public Set<Question> getQuestons() {
        return questions;
    }

    public void setQuestons(Set<Question> questions) {
        this.questions = questions;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

}

package de.kit.recruitergame.model;

import jakarta.persistence.*;

@Entity
public class RecruiterAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Recruiter recruiter;

    @ManyToOne
    private Answer answer;

    private boolean recruiterAnser;

    public RecruiterAnswer() {
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public Answer getAnswer() {
        return answer;
    }

    public boolean isRecruiterAnser() {
        return recruiterAnser;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setRecruiterAnser(boolean recruiterAnser) {
        this.recruiterAnser = recruiterAnser;
    }
}

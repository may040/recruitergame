package de.kit.recruitergame.dto;

public class RecruiterDTO {

    private String name;

    private String company;

    private int achievedPoints;

    public RecruiterDTO(String name, String company, int achievedPoints) {
        this.name = name;
        this.company = company;
        this.achievedPoints = achievedPoints;
    }

    public RecruiterDTO(){

    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public int getAchievedPoints() {
        return achievedPoints;
    }
}

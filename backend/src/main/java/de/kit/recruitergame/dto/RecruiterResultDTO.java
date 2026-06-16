package de.kit.recruitergame.dto;

public class RecruiterResultDTO {

    private String name;
    private String company;
    private int achievedPoints;

    // Standard-Konstruktor
    public RecruiterResultDTO() {
    }

    // All-Args-Konstruktor
    public RecruiterResultDTO(String name, String company, int achievedPoints) {
        this.name = name;
        this.company = company;
        this.achievedPoints = achievedPoints;
    }

    // Getter und Setter
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

    public int getAchievedPoints() {
        return achievedPoints;
    }

    public void setAchievedPoints(int achievedPoints) {
        this.achievedPoints = achievedPoints;
    }
}

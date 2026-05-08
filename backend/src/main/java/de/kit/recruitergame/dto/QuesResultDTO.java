package de.kit.recruitergame.dto;

public class QuesResultDTO {

    private String correctAnswer;
    private boolean answeredCorrect;

    public QuesResultDTO(String correctAnswer, boolean answeredCorrect) {
        this.correctAnswer = correctAnswer;
        this.answeredCorrect = answeredCorrect;
    }

    public QuesResultDTO() {

    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isAnsweredCorrect() {
        return answeredCorrect;
    }

    public void setAnsweredCorrect(boolean answeredCorrect) {
        this.answeredCorrect = answeredCorrect;
    }
}

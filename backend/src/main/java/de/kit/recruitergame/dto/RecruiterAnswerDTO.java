package de.kit.recruitergame.dto;

public class RecruiterAnswerDTO {
    private int recruiter_id;
    private int answer_id;
    private boolean selected;

    public RecruiterAnswerDTO() {
    }

    public RecruiterAnswerDTO(int recruiter_id, int answer_id, boolean selected) {
        this.recruiter_id = recruiter_id;
        this.answer_id = answer_id;
        this.selected = selected;
    }

    public int getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(int recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}

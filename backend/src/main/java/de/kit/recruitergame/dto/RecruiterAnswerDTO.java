package de.kit.recruitergame.dto;

public class RecruiterAnswerDTO {
    private Long recruiter_id;
    private Long answer_id;
    private boolean selected;

    public RecruiterAnswerDTO() {
    }

    public RecruiterAnswerDTO(Long recruiter_id, Long answer_id, boolean selected) {
        this.recruiter_id = recruiter_id;
        this.answer_id = answer_id;
        this.selected = selected;
    }

    public Long getRecruiter_id() {
        return recruiter_id;
    }

    public void setRecruiter_id(Long recruiter_id) {
        this.recruiter_id = recruiter_id;
    }

    public Long getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}

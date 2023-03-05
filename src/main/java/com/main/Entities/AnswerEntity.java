package com.main.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Entity
@JsonIgnoreProperties({"exam"})
@Table(name = "student_answer",indexes = @Index(columnList = "id"))
public class AnswerEntity {
    //region Property
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private UUID questionId;
    private Integer answer;
    private Boolean isCorrect;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_exam_id")
    StudentExamEntity exam;
    //endregion
    //region Constructor

    public AnswerEntity( UUID questionId, Integer answer, Boolean isCorrect, StudentExamEntity exam) {
        this.questionId = questionId;
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.exam = exam;
    }

    public AnswerEntity() {
    }

    //endregion
    //region Getter & Setter

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public StudentExamEntity getExam() {
        return exam;
    }

    public void setExam(StudentExamEntity exam) {
        this.exam = exam;
    }

//endregion

}

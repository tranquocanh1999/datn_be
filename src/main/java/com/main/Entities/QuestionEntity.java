package com.main.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "question", indexes = @Index(columnList = "id"))
public class QuestionEntity {
    //region Property
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(unique = true)
    private String code;

    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject = new SubjectEntity();

    @Column
    private String note;

    @Column
    private Integer level;

    @ElementCollection
    @JoinTable(name = "question_answer", joinColumns = @JoinColumn(name = "question_answer_id"))
    @Column(name = "choice_answers")
    private List<String> choiceAnswers= new ArrayList<String>();

    @Column
    private Integer correctAnswer;

    @Column
    private Date deleteAt;

    @Column
    private UUID createBy;

    @Column
    private Date createAt;

    @Column
    private UUID updateBy;

    @Column
    private Date updateAt;


    //endregion

    //region Constructor
    //endregion

    //region Getter & Setter

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<String> getChoiceAnswers() {
        return choiceAnswers;
    }

    public void setChoiceAnswers(List<String> choiceAnswers) {
        this.choiceAnswers = choiceAnswers;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public UUID getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UUID createBy) {
        this.createBy = createBy;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public UUID getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(UUID updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //endregion
}

package com.main.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.main.Models.ExamForm;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exam", indexes = @Index(columnList = "id"))
public class ExamEntity {
    //region Property
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column
    private String code;


    @ManyToOne(optional = false)
    @JoinColumn(name = "competition_id")
    private CompetitionEntity competition= new CompetitionEntity();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Where(clause = "delete_at is null and level = 1")
    private List<QuestionIndelibilityEntity> questionLv1 = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Where(clause = "delete_at is null and level = 2")
    private List<QuestionIndelibilityEntity> questionLv2 = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Where(clause = "delete_at is null and level = 3")
    private List<QuestionIndelibilityEntity> questionLv3 = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Where(clause = "delete_at is null and level = 4")
    private List<QuestionIndelibilityEntity> questionLv4 = new ArrayList<>();

    @Column
    private Integer status;

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

    public List<QuestionIndelibilityEntity> getQuestionLv1() {
        return questionLv1;
    }

    public void setQuestionLv1(List<QuestionIndelibilityEntity> questionLv1) {
        this.questionLv1 = questionLv1;
    }

    public List<QuestionIndelibilityEntity> getQuestionLv2() {
        return questionLv2;
    }

    public void setQuestionLv2(List<QuestionIndelibilityEntity> questionLv2) {
        this.questionLv2 = questionLv2;
    }

    public List<QuestionIndelibilityEntity> getQuestionLv3() {
        return questionLv3;
    }

    public void setQuestionLv3(List<QuestionIndelibilityEntity> questionLv3) {
        this.questionLv3 = questionLv3;
    }

    public List<QuestionIndelibilityEntity> getQuestionLv4() {
        return questionLv4;
    }

    public void setQuestionLv4(List<QuestionIndelibilityEntity> questionLv4) {
        this.questionLv4 = questionLv4;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
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

    public CompetitionEntity getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionEntity competition) {
        this.competition = competition;
    }

//endregion
}

package com.main.Entities;

import com.main.Models.ExamForm;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "competition", indexes = @Index(columnList = "id"))
public class CompetitionEntity {
    //region Property
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column
    private String code;

    @Column
    private String title;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject = new SubjectEntity();

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classroom ;

    @Column
    private Integer time;

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

    public CompetitionEntity() {
    }

    public CompetitionEntity(ExamForm exam) {
        this.title = exam.getTitle();
        this.time = exam.getTime();
        this.status = 0;
        this.code=exam.getCode();
        this.classroom= new ClassEntity(exam.getClassroom());
        this.subject = exam.getSubject();
    }
    //endregion

    //region Getter & Setter

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String content) {
        this.title = content;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ClassEntity getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassEntity classroom) {
        this.classroom = classroom;
    }

    //endregion
}

package com.main.Entities;

import com.main.Models.ExamForm;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity
@Table(name = "user_exam", indexes = @Index(columnList = "id"))
public class StudentExamEntity {
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
    @JoinColumn(name = "exam_id")
    private ExamEntity exam = new ExamEntity();

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student ;

    @Column
    private Long degree;

    @Column
    private Integer status;

    @Column
    private Date startAt;

    @ElementCollection
    @JoinTable(name = "student_answer", joinColumns = @JoinColumn(name = "student_answer_id"))
    @Column(name = "student_answers")
    private Map<UUID,Integer> answers= new HashMap<>();

    @Column
    private UUID createBy;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ExamEntity getExam() {
        return exam;
    }

    public void setExam(ExamEntity exam) {
        this.exam = exam;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public Long getDegree() {
        return degree;
    }

    public void setDegree(Long degree) {
        this.degree = degree;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Map<UUID, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<UUID, Integer> answers) {
        this.answers = answers;
    }

    public UUID getCreateBy() {
        return createBy;
    }

    public void setCreateBy(UUID createBy) {
        this.createBy = createBy;
    }

    //endregion
}

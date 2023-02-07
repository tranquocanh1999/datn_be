package com.main.Models;

import com.main.Entities.ClassEntity;
import com.main.Entities.CompetitionEntity;
import com.main.Entities.SubjectEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

public class Competition {

    //region Property
    private UUID id;
    private String code;
    private String title;
    private SubjectEntity subject = new SubjectEntity();
    private ClassModel2 classroom ;
    private Integer time;
    private Integer status;
    private Double degree;
    //endregion

    //region Constructor

    public Competition() {
    }

    public Competition(CompetitionEntity competition) {
        this.id = competition.getId();
        this.code = competition.getCode();
        this.title = competition.getTitle();
        this.subject = competition.getSubject();
        this.classroom = new ClassModel2(competition.getClassroom());
        this.time = competition.getTime();
        this.status = competition.getStatus();
    }

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

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public ClassModel2 getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassModel2 classroom) {
        this.classroom = classroom;
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

    public Double getDegree() {
        return degree;
    }

    public void setDegree(Double degree) {
        this.degree = degree;
    }
//endregion
}

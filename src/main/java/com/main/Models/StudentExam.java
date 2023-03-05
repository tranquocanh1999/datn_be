package com.main.Models;

import com.main.Entities.AnswerEntity;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class StudentExam {
    private UUID id;
    private String fullName;
    private String status;
    private String degree;
    private String startAt;
    private String endAt;

    private UUID examID;

    private List<AnswerEntity> answers;

    public StudentExam(List<String> list) {


        this.id = UUID.fromString(list.get(0));
        this.fullName = list.get(1);
        this.status = list.get(3);
        this.degree = list.get(2);
        if (list.get(4) != null) {
            this.examID = UUID.fromString(list.get(4));
        }
        if (list.get(5) != null) {
            this.startAt = list.get(5).split("\\.")[0];
        }
        if (list.get(6) != null) {
            this.endAt = list.get(6).split("\\.")[0];
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public UUID getExamID() {
        return examID;
    }

    public void setExamID(UUID examID) {
        this.examID = examID;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }
}

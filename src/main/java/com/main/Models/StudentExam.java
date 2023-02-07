package com.main.Models;

import java.util.List;

public class StudentExam {
    private String id;
    private String fullName;
    private String status;
    private String degree;

    public StudentExam(List<String> list) {
        this.id= list.get(0);
        this.fullName= list.get(1);
        this.status= list.get(3);
        this.degree= list.get(2);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}

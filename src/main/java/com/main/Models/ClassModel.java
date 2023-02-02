package com.main.Models;

import com.main.Entities.ClassEntity;
import java.util.UUID;

public class ClassModel {
    //region Property

    private UUID id;


    private String classCode;


    private String className;


    private String description;


    private Integer numberOfTeacher;

    private Integer numberOfStudent;

    //endregion

    //region Constructor

    public ClassModel(ClassEntity classEntity) {
        this.id = classEntity.getId();
        this.classCode = classEntity.getClassCode();
        this.className = classEntity.getClassName();
        this.description = classEntity.getDescription();
        this.numberOfTeacher= classEntity.getTeachers().size();
        this.numberOfStudent=classEntity.getStudents().size();
    }

    //endregion

    //region Getter & Setter


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfTeacher() {
        return numberOfTeacher;
    }

    public void setNumberOfTeacher(Integer numberOfTeacher) {
        this.numberOfTeacher = numberOfTeacher;
    }

    public Integer getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(Integer numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    //endregion
}

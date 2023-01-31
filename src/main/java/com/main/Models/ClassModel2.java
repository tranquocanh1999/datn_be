package com.main.Models;

import com.main.Entities.ClassEntity;

import java.util.UUID;

public class ClassModel2 {
    //region Property

    private UUID id;


    private String classCode;


    private String className;



    //endregion

    //region Constructor

    public ClassModel2(ClassEntity classEntity) {
        this.id = classEntity.getId();
        this.classCode = classEntity.getClassCode();
        this.className = classEntity.getClassName();
    }

    public ClassModel2() {
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


    //endregion
}

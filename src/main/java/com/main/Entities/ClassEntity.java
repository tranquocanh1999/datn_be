package com.main.Entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.main.Models.ClassModel2;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;

import java.util.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "class", indexes = @Index(columnList = "id"))
public class ClassEntity {
    //region Property
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(unique = true)
    private String classCode;

    @Column
    private String className;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom", cascade = CascadeType.ALL)
    @Where(clause = "delete_at is null")
    private List<TeacherClassEntity> teachers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classroom", cascade = CascadeType.ALL)
    @Where(clause = "delete_at is null")
    private List<StudentClassEntity> students = new ArrayList<>();


    @Column
    private UUID createBy;

    @Column
    private Date createAt;

    @Column
    private UUID updateBy;

    @Column
    private Date updateAt;

    @Column
    private Date deleteAt;
    //endregion

    //region Constructor
    public ClassEntity(ClassModel2 classEntity) {
        this.id = classEntity.getId();
        this.classCode = classEntity.getClassCode();
        this.className = classEntity.getClassName();
    }

    public ClassEntity(UUID id) {
        this.id = id;
    }

    public ClassEntity() {
    }

    //endregion

    //region Getter & Setter

    public List<StudentClassEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentClassEntity> students) {
        this.students = students;
    }

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

    public List<TeacherClassEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherClassEntity> teachers) {
        this.teachers = teachers;
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
    //endregion
}

package com.main.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "teacher_class",indexes = @Index(columnList = "id"))

public class TeacherClassEntity {
    //region Property
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id")
    TeacherEntity teacher;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_id")
    ClassEntity classroom;

    @Column
    private Date deleteAt;

    //endregion

    //region Constructor
    //endregion

    //region Getter & Setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public ClassEntity getClassroom() {
        return classroom;
    }
    public void setClassroom(ClassEntity classroom) {
        this.classroom = classroom;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }

    //endregion
}
package com.main.Entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;

import java.util.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

@Table(name = "teacher",indexes = @Index(columnList = "id"))
public class TeacherEntity {
    //region Property
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @Where(clause = "delete_at is null")
    private List<TeacherClassEntity> classes = new ArrayList<>();

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @Where(clause = "delete_at is null")
    private List<TeacherSubjectEntity> subjects = new ArrayList<>();

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
    //endregion

    //region Getter & Setter

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<TeacherClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<TeacherClassEntity> classes) {
        this.classes = classes;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public List<TeacherSubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<TeacherSubjectEntity> subjects) {
        this.subjects = subjects;
    }

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
//endregion
}

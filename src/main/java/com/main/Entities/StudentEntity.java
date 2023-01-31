package com.main.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.main.Models.Student;
import jakarta.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;

import java.util.*;

@Entity
@Table(name = "student", indexes = @Index(columnList = "id"))
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class StudentEntity {
    //region Property
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @OneToOne
    private UserEntity user;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @Where(clause = "delete_at is null")
    private List<StudentClassEntity> classes = new ArrayList<>();

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

    public List<StudentClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<StudentClassEntity> classes) {
        this.classes = classes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Date getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Date deleteAt) {
        this.deleteAt = deleteAt;
    }
    //endregion
}

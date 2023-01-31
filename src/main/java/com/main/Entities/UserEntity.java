package com.main.Entities;

import com.main.Models.Student;
import jakarta.persistence.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "user", indexes = @Index(columnList = "id"))
public class UserEntity implements Serializable {
    //region Property
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;


    @Column(unique = true)
    private String username;
    @Column
    private String password;

    @Column
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    private String email;
    private String phoneNumber;
    private Date birthday;
    private Integer gender;

    //endregion

    //region Constructor

    public UserEntity(Student student) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.username = student.getUsername();
        this.password = student.getPassword();
        this.fullName = student.getFullName();
        this.email = student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
        try {
            this.birthday = dateFormat.parse(student.getBirthday());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.gender = student.getGender();
    }

    public UserEntity() {
    }


    //endregion

    //region Getter & Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public RoleEntity getRole() {
        return role;
    }

    public Long getRoleID() {
        return role.getId();
    }


    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}

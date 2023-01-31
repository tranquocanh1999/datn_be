package com.main.Models;

import com.main.Entities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Teacher {
    //region Property
    private UUID id;
    private String username;
    private String fullName;
    private RoleEntity role;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private Integer gender;
    private List<ClassEntity> classes = new ArrayList<>();
    //endregion

    //region Constructor

    public Teacher(TeacherEntity teacher) {
        if(teacher!=null){
        UserEntity user = teacher.getUser();
        this.username = user.getUsername();
        this.id = user.getId();
        this.role = user.getRole();
        this.fullName = user.getFullName();
        this.birthday = user.getBirthday();
        this.role = user.getRole();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();}
    }

    //endregion
    //region Getter & Setter

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

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

    public List<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }


    //endregion
}

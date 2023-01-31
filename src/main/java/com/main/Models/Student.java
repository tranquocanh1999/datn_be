package com.main.Models;

import com.main.Entities.*;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class Student implements Serializable {
    //region Property
    private UUID id;
    private String username;
    private String fullName;
    private RoleEntity role;
    private String email;
    private String phoneNumber;
    private String birthday;
    private Integer gender;

    private String password;
    private List<ClassModel2> classes = new ArrayList<>();
    //endregion

    //region Constructor
    public Student(StudentEntity student) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        UserEntity user = student.getUser();
        this.username = user.getUsername();
        this.id = student.getId();
        this.role = user.getRole();
        this.fullName = user.getFullName();
        this.birthday = dateFormat.format(user.getBirthday());
        this.role = user.getRole();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.phoneNumber = user.getPhoneNumber();
         student.getClasses().forEach(item->{
             this.classes.add(new ClassModel2(item.getClassroom()));
         });
    }

    public Student() {
    }

    //endregion
    //region Getter & Setter
    public RoleEntity getRole() {
        return role;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public List<ClassModel2> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassModel2> classes) {
        this.classes = classes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //endregion

}

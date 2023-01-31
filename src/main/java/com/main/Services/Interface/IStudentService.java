package com.main.Services.Interface;

import com.main.Entities.ClassEntity;
import com.main.Entities.StudentEntity;
import com.main.Models.*;

import java.util.UUID;

public interface IStudentService {
    PageModal<Student> findMany(Search data);
    StudentEntity delete(UUID id);
    Student getStudent(UUID id);
    Message createStudent(Student data) throws CommonException;
    Message updateStudent(Student data) throws CommonException;
}

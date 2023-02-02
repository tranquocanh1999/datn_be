package com.main.Shared.Mapper;

import com.main.Entities.StudentEntity;
import com.main.Entities.TeacherEntity;
import com.main.Models.Student;
import com.main.Models.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    public List<Student> getListStudent(List<StudentEntity> studentEntities){
        List<Student> studentList= new ArrayList<>();
        studentEntities.forEach(item->{
            studentList.add(new Student(item));
        });
        return studentList;
    }

    public List<Teacher> getListTeacher(List<TeacherEntity> teacherEntities){
        List<Teacher> teacherList= new ArrayList<>();
        teacherEntities.forEach(item->{
            teacherList.add(new Teacher(item));
        });
        return teacherList;
    }

}

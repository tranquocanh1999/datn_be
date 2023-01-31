package com.main.Shared.Mapper;

import com.main.Entities.StudentEntity;
import com.main.Models.Student;
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

}

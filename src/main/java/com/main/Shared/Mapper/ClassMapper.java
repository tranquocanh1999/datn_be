package com.main.Shared.Mapper;

import com.main.Entities.ClassEntity;
import com.main.Entities.StudentEntity;
import com.main.Models.ClassModel;
import com.main.Models.ClassModel2;
import com.main.Models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassMapper {
    public List<ClassModel> getListClass(List<ClassEntity> classEntities){
        List<ClassModel> studentList= new ArrayList<>();
        classEntities.forEach(item->{
            studentList.add(new ClassModel(item));
        });
        return studentList;
    }

    public List<ClassModel2> getListClass2(List<ClassEntity> classEntities){
        List<ClassModel2> studentList= new ArrayList<>();
        classEntities.forEach(item->{
            studentList.add(new ClassModel2(item));
        });
        return studentList;
    }
}

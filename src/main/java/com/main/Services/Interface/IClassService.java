package com.main.Services.Interface;

import com.main.Entities.ClassEntity;
import com.main.Entities.TeacherEntity;
import com.main.Models.*;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface IClassService {
    PageModal<ClassModel> findMany(Search data);
    ClassEntity delete(UUID id);
    ClassModel getClass(UUID id);
    Message createClass(ClassEntity data) throws CommonException;
    Message updateClass(ClassEntity data) throws CommonException;
    List<ClassModel2> getAll();

}

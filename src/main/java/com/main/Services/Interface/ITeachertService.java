package com.main.Services.Interface;

import com.main.Entities.TeacherEntity;
import com.main.Models.*;

import java.util.UUID;

public interface ITeachertService {
    PageModal<Teacher> findMany(Search data);
    TeacherEntity delete(UUID id);
    Teacher getTeacher(UUID id);
    Message createTeacher(Teacher data) throws CommonException;
    Message updateTeacher(Teacher data) throws CommonException;
}

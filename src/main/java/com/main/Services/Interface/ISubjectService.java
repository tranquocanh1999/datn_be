package com.main.Services.Interface;

import com.main.Entities.ClassEntity;
import com.main.Entities.SubjectEntity;
import com.main.Models.*;

import java.util.List;
import java.util.UUID;

public interface ISubjectService {
    List<SubjectEntity> getAll();
}

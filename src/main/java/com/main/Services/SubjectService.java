package com.main.Services;

import com.main.Entities.ClassEntity;
import com.main.Entities.SubjectEntity;
import com.main.Models.*;
import com.main.Repositories.ClassRepository;
import com.main.Repositories.SubjectRepository;
import com.main.Repositories.TeacherRepository;
import com.main.Repositories.UserRepository;
import com.main.Services.Interface.IClassService;
import com.main.Services.Interface.ISubjectService;
import com.main.Shared.Enums.ClassMessage;
import com.main.Shared.Mapper.ClassMapper;
import com.main.Shared.Services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SubjectService implements ISubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<SubjectEntity> getAll() {
        List<SubjectEntity> subjectEntities=subjectRepository.findAll();
        return subjectEntities;
    }
}

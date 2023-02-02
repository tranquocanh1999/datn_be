package com.main.Services;

import com.main.Entities.ClassEntity;
import com.main.Models.*;
import com.main.Repositories.ClassRepository;
import com.main.Repositories.TeacherRepository;
import com.main.Repositories.UserRepository;
import com.main.Services.Interface.IClassService;
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
public class ClassService implements IClassService {
    @Autowired
    ClassRepository classRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClassMapper classMapper;
    @Autowired
    CommonService commonService;

    private String classCodePre = "LH_";

    @Override
    public PageModal<ClassModel> findMany(Search data) {
        Pageable pageRequest = commonService.getPage(data);

        final String[] classCode = {""};
        final String[] className = {""};
        if (data.getFilters().stream().count() > 0) {
            data.getFilters().stream().forEach(item -> {
                if (item.getName().equals("classCode")) {
                    classCode[0] = item.getValue();
                } else if (item.getName().equals("className")) {
                    className[0] = item.getValue();
                }
            });
        }
        Page<ClassEntity> page = classRepository.findAllByClassCodeContainsAndClassNameContainsAndDeleteAtIsNull(
                classCode[0], className[0], pageRequest
        );

        List<ClassModel> classList = classMapper.getListClass(page.getContent());
        PageModal<ClassModel> result = new PageModal<>(page, classList);

        return result;
    }

    @Override
    public List<ClassModel2> getAll() {
        List<ClassEntity> classEntities=classRepository.findAllByDeleteAtIsNull();
        return classMapper.getListClass2(classEntities);
    }

    @Override
    public ClassEntity delete(UUID id) {
        ClassEntity entity = classRepository.findByIdAndDeleteAtIsNull(id);
        entity.setDeleteAt(new Date());
        return classRepository.save(entity);
    }

    @Override
    public ClassModel getClass(UUID id) {
        ClassEntity result = classRepository.findByIdAndDeleteAtIsNull(id);
        result.getClass();
        return new ClassModel(result);
    }

    @Override
    public Message createClass(ClassEntity data) throws CommonException {
        Message message = validate(data);
        if (message == null) {
            String code = classRepository.getClassCode();
            if (code != null) {
                Integer index = Integer.parseInt(classRepository.getClassCode().split(this.classCodePre)[1]) + 1;
                String classCode = this.classCodePre + index;
                data.setClassCode(classCode);
            } else data.setClassCode("LH_1");
            data.setCreateAt(new Date());
            data.setCreateBy(userRepository.findByUsernameAndDeleteAtIsNull(SecurityContextHolder.getContext().getAuthentication().getName()).getId()
            );
            classRepository.save(data);
        } else {
            throw new CommonException(message);
        }
        message = new Message();
        message.setStatus(200);
        message.setMgsCode("CREATE_SUCCESS");
        message.setMessage(ClassMessage.CREATE_SUCCESS.value());
        return message;
    }

    @Override
    public Message updateClass(ClassEntity data) throws CommonException {
        Message message = validate(data);
        ClassEntity classEntity = classRepository.findByIdAndDeleteAtIsNull(data.getId());
        if (classEntity == null) {
            message.setStatus(400);
            message.setMgsCode("CLASS_NOT_EXIST");
            message.setMessage(ClassMessage.CLASS_NOT_EXIST.value());
        }
        if (message == null) {
            classEntity.setUpdateAt(new Date());
            classEntity.setUpdateBy(userRepository.findByUsernameAndDeleteAtIsNull(SecurityContextHolder.getContext().getAuthentication().getName()).getId()
            );
            classEntity.setClassName(data.getClassName());
            classEntity.setDescription(data.getDescription());
            classRepository.save(classEntity);
        } else {
            throw new CommonException(message);
        }
        message = new Message();
        message.setStatus(200);
        message.setMgsCode("UPDATE_SUCCESS");
        message.setMessage(ClassMessage.UPDATE_SUCCESS.value());
        return message;
    }

    private Message validate(ClassEntity data) {
        Message message = new Message();
        message.setStatus(400);
        if (data.getClassName().equals("")) {
            message.setMgsCode("NAME_NOT_NULL");
            message.setMessage(ClassMessage.NAME_NOT_NULL.value());
        } else if (data.getClassName().length() > 255) {
            message.setMgsCode("NAME_MAX_LENGTH");
            message.setMessage(ClassMessage.NAME_MAX_LENGTH.value());
        } else if (data.getDescription().length() > 255) {
            message.setMgsCode("DESCRIPTION_MAX_LENGTH");
            message.setMessage(ClassMessage.DESCRIPTION_MAX_LENGTH.value());
        }
        if (message.getMessage() == null) {
            return null;
        } else return message;
    }

}

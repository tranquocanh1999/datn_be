package com.main.Services;

import com.main.Entities.*;
import com.main.Models.*;
import com.main.Repositories.*;
import com.main.Services.Interface.ITeachertService;
import com.main.Shared.Enums.StudentMessage;
import com.main.Shared.Enums.TeacherMessage;
import com.main.Shared.Mapper.UserMapper;
import com.main.Shared.Services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class TeacherService implements ITeachertService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TeacherClassRepository teacherClassRepository;

    @Autowired
    TeacherSubjectRepository teacherSubjectRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Autowired
    CommonService commonService;


    @Override
    public PageModal<Teacher> findMany(Search data) {
        Pageable pageRequest = commonService.getPage(data);

        final String[] username = {""};
        final String[] fullName = {""};
        final String[] classID = {""};
        final String[] subjectID = {""};
        if (data.getFilters().stream().count() > 0) {
            data.getFilters().stream().forEach(item -> {
                if (item.getName().equals("username")) {
                    username[0] = item.getValue();
                } else if (item.getName().equals("fullName")) {
                    fullName[0] = item.getValue();
                } else if (item.getName().equals("classID")) {
                    classID[0] = item.getValue();
                } else if (item.getName().equals("subjectID")) {
                    subjectID[0] = item.getValue();
                }
            });
        }
        Page<TeacherEntity> page;
        if (classID[0].equals("") && subjectID[0].equals("")) {
            page = teacherRepository.findAllByUserFullNameContainsAndUserUsernameContainsAndDeleteAtIsNull(
                    fullName[0], username[0], pageRequest
            );
        } else if (classID[0].equals("")) {
            page = teacherRepository.findAllByUserFullNameContainsAndUserUsernameContainsAndSubjectsSubjectIdAndDeleteAtIsNull(
                    fullName[0], username[0], Long.parseLong(subjectID[0]), pageRequest
            );
        } else if (subjectID[0].equals("")) {
            page = teacherRepository.findAllByUserFullNameContainsAndUserUsernameContainsAndClassesClassroomIdAndDeleteAtIsNull(
                    fullName[0], username[0], UUID.fromString(classID[0]), pageRequest
            );
        } else {
            page = teacherRepository.findAllByUserFullNameContainsAndUserUsernameContainsAndClassesClassroomIdAndSubjectsSubjectIdAndDeleteAtIsNull(
                    fullName[0], username[0], UUID.fromString(classID[0]), Long.parseLong(subjectID[0]), pageRequest
            );
        }
        List<Teacher> teacherList = userMapper.getListTeacher(page.getContent());
        PageModal<Teacher> result = new PageModal<>(page, teacherList);
        return result;
    }

    @Override
    public TeacherEntity delete(UUID id) {
        TeacherEntity entity = teacherRepository.findByIdAndDeleteAtIsNull(id);
        entity.setDeleteAt(new Date());
        teacherRepository.save(entity);
        UserEntity user= entity.getUser();
        user.setDeleteAt(new Date());
        userRepository.save(user);
        List<TeacherClassEntity> teacherClassEntities = teacherClassRepository.getAllByTeacherIdAndDeleteAtIsNull(id);
        for (TeacherClassEntity teacherClassEntity : teacherClassEntities) {
            teacherClassEntity.setDeleteAt(new Date());
        }
        teacherClassRepository.saveAll(teacherClassEntities);

        List<TeacherSubjectEntity> teacherSubjectEntities = teacherSubjectRepository.getAllByTeacherIdAndDeleteAtIsNull(id);
        for (TeacherSubjectEntity teacherSubjectEntity : teacherSubjectEntities) {
            teacherSubjectEntity.setDeleteAt(new Date());
        }
        teacherSubjectRepository.saveAll(teacherSubjectEntities);
        return null;
    }

    @Override
    public Teacher getTeacher(UUID id) {
        TeacherEntity result = teacherRepository.findByIdAndDeleteAtIsNull(id);
        result.getUser();
        return new Teacher(result);
    }

    @Override
    public Message createTeacher(Teacher data) throws CommonException {
        Message message = validate(data, false);
        if (message == null) {
            UserEntity user = new UserEntity(data);
            user.setPassword(commonService.encryptPass(user.getPassword()));
            TeacherEntity teacher = new TeacherEntity();
            teacher.setCreateAt(new Date());
            teacher.setCreateBy(userRepository.findByUsernameAndDeleteAtIsNull(SecurityContextHolder.getContext().getAuthentication().getName()).getId()
            );
            teacher.setUser(userRepository.save(user));
            TeacherEntity teacherEntity = teacherRepository.save(teacher);
            data.getClasses().forEach(item -> {
                ClassEntity classEntity = new ClassEntity(item.getId());
                TeacherClassEntity teacherClassEntity = new TeacherClassEntity();
                teacherClassEntity.setTeacher(teacherEntity);
                teacherClassEntity.setClassroom(classEntity);
                teacherClassRepository.save(teacherClassEntity);
            });
            data.getSubjects().forEach(item -> {
                SubjectEntity subjectEntity = new SubjectEntity(item.getId());
                TeacherSubjectEntity  teacherSubjectEntity = new TeacherSubjectEntity();
                teacherSubjectEntity.setTeacher(teacherEntity);
                teacherSubjectEntity.setSubject(subjectEntity);
                teacherSubjectRepository.save(teacherSubjectEntity);
            });
        } else {
            throw new CommonException(message);
        }
        message = new Message();
        message.setStatus(200);
        message.setMgsCode("CREATE_SUCCESS");
        message.setMessage(TeacherMessage.CREATE_SUCCESS.value());
        return message;
    }

    @Override
    public Message updateTeacher(Teacher data) throws CommonException {
        UserEntity userEntity = userRepository.findByUsernameAndDeleteAtIsNull(data.getUsername());
        userEntity.getId();
        Message message = validate(data, true);
        if (message == null) {
            UserEntity user = new UserEntity(data);
            user.setId(userEntity.getId());
            if (!user.getPassword().equals("")) {
                user.setPassword(commonService.encryptPass(user.getPassword()));
            } else {
                user.setPassword(userEntity.getPassword());
            }
            userRepository.save(user);
            TeacherEntity teacher = teacherRepository.findByIdAndDeleteAtIsNull(data.getId());
            teacher.setUpdateAt(new Date());
            teacher.setUpdateBy(userRepository.findByUsernameAndDeleteAtIsNull(SecurityContextHolder.getContext().getAuthentication().getName()).getId()
            );
            teacherRepository.save(teacher);
            List<TeacherClassEntity> teacherClassEntities = teacherClassRepository.getAllByTeacherIdAndDeleteAtIsNull(teacher.getId());

            for (TeacherClassEntity teacherClassEntity : teacherClassEntities) {
                teacherClassEntity.setDeleteAt(new Date());
            }

            teacherClassRepository.saveAll(teacherClassEntities);

            List<TeacherSubjectEntity> teacherSubjectEntities = teacherSubjectRepository.getAllByTeacherIdAndDeleteAtIsNull(teacher.getId());
            for (TeacherSubjectEntity teacherSubjectEntity : teacherSubjectEntities) {
                teacherSubjectEntity.setDeleteAt(new Date());
            }
            teacherSubjectRepository.saveAll(teacherSubjectEntities);

            data.getClasses().forEach(item -> {
                ClassEntity classEntity = new ClassEntity(item.getId());
                TeacherClassEntity teacherClassEntity = new TeacherClassEntity();
                teacherClassEntity.setTeacher(teacher);
                teacherClassEntity.setClassroom(classEntity);
                teacherClassRepository.save(teacherClassEntity);
            });
            data.getSubjects().forEach(item -> {
                SubjectEntity subjectEntity = new SubjectEntity(item.getId());
                TeacherSubjectEntity  teacherSubjectEntity = new TeacherSubjectEntity();
                teacherSubjectEntity.setTeacher(teacher);
                teacherSubjectEntity.setSubject(subjectEntity);
                teacherSubjectRepository.save(teacherSubjectEntity);
            });
        } else {
            throw new CommonException(message);
        }
        return message;
    }

    private Message validate(Teacher data, Boolean isEdit) {
        Pattern patternMail = Pattern.compile("^[a-zA-Z0-9\\+\\.\\_%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,62}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+$");
        Message message = new Message();
        message.setStatus(400);
        if (data.getUsername().equals("")) {
            message.setMgsCode("USERNAME_NOT_NULL");
            message.setMessage(StudentMessage.USERNAME_NOT_NULL.value());
        } else if (data.getUsername().length() > 255) {
            message.setMgsCode("USERNAME_MAX_LENGTH");
            message.setMessage(StudentMessage.USERNAME_MAX_LENGTH.value());
        } else if (data.getFullName().equals("")) {
            message.setMgsCode("NAME_NOT_NULL");
            message.setMessage(StudentMessage.NAME_NOT_NULL.value());
        } else if (data.getFullName().length() > 255) {
            message.setMgsCode("NAME_MAX_LENGTH");
            message.setMessage(StudentMessage.NAME_MAX_LENGTH.value());
        } else if (data.getEmail().equals("")) {
            message.setMgsCode("EMAIL_NOT_NULL");
            message.setMessage(StudentMessage.EMAIL_NOT_NULL.value());
        } else if (!patternMail.matcher(data.getEmail()).matches()) {
            message.setMgsCode("EMAIL_NOT_CORRECT");
            message.setMessage(StudentMessage.EMAIL_NOT_CORRECT.value());
        } else if (data.getPhoneNumber().equals("")) {
            message.setMgsCode("PHONE_NUMBER_NOT_NULL");
            message.setMessage(StudentMessage.PHONE_NUMBER_NOT_NULL.value());
        } else if (data.getPhoneNumber().length() > 10) {
            message.setMgsCode("PHONE_NUMBER_MAX_LENGTH");
            message.setMessage(StudentMessage.PHONE_NUMBER_MAX_LENGTH.value());
        } else if (data.getPassword().equals("") && !isEdit) {
            message.setMgsCode("PASSWORD_NOT_NULL");
            message.setMessage(StudentMessage.PASSWORD_NOT_NULL.value());
        } else if (data.getPassword().length() > 255 && !isEdit) {
            message.setMgsCode("PASSWORD_MAX_LENGTH");
            message.setMessage(StudentMessage.PASSWORD_MAX_LENGTH.value());
        } else if (data.getPassword().length() < 8 && !isEdit) {
            message.setMgsCode("PASSWORD_MIN_LENGTH");
            message.setMessage(StudentMessage.PASSWORD_MIN_LENGTH.value());
        }
        UserEntity user = userRepository.findByUsername(data.getUsername());
        TeacherEntity teacher = teacherRepository.findByIdAndDeleteAtIsNull(data.getId());
        if (user != null &&  (teacher==null||!user.getId().equals(teacher.getUser().getId()))) {
            message.setMgsCode("USERNAME_IS_USED");
            message.setMessage(StudentMessage.USERNAME_IS_USED.value());
        }

        if (message.getMessage() == null) {
            return null;
        } else return message;


    }

}

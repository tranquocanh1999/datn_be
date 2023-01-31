package com.main.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.main.Entities.*;
import com.main.Models.*;
import com.main.Repositories.StudentClassRepository;
import com.main.Repositories.StudentRepository;
import com.main.Repositories.TeacherRepository;
import com.main.Repositories.UserRepository;
import com.main.Services.Interface.IStudentService;
import com.main.Shared.Enums.ClassMessage;
import com.main.Shared.Enums.StudentMessage;
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
public class StudentService implements IStudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentClassRepository studentClassRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Autowired
    CommonService commonService;

    private String classCodePre = "LH_";

    @Override
    public PageModal<Student> findMany(Search data) {
        Pageable pageRequest = commonService.getPage(data);

        final String[] username = {""};
        final String[] fullName = {""};
        final String[] classID = {""};
        if (data.getFilters().stream().count() > 0) {
            data.getFilters().stream().forEach(item -> {
                if (item.getName().equals("username")) {
                    username[0] = item.getValue();
                } else if (item.getName().equals("fullName")) {
                    fullName[0] = item.getValue();
                } else if (item.getName().equals("classID")) {
                    classID[0] = item.getValue();
                }
            });
        }
        Page<StudentEntity> page;
        if (classID[0].equals("")) {
            page = studentRepository.findAllByUserFullNameContainsAndUserUsernameContainsAndDeleteAtIsNull(
                    fullName[0], username[0], pageRequest
            );
        } else {
            page = studentRepository.findAllByUserFullNameContainsAndUserUsernameContainsAndClassesClassroomIdAndDeleteAtIsNull(
                    fullName[0], username[0], UUID.fromString(classID[0]), pageRequest
            );
        }
        List<Student> studentList = userMapper.getListStudent(page.getContent());
        PageModal<Student> result = new PageModal<>(page, studentList);


        return result;
    }

    @Override
    public StudentEntity delete(UUID id) {
        StudentEntity entity = studentRepository.findByIdAndDeleteAtIsNull(id);
        entity.setDeleteAt(new Date());
        studentRepository.save(entity);
        List<StudentClassEntity> studentClassEntities = studentClassRepository.getAllByStudentIdAndDeleteAtIsNull(id);

        for (StudentClassEntity studentClassEntity : studentClassEntities) {
            studentClassEntity.setDeleteAt(new Date());
        }

        studentClassRepository.saveAll(studentClassEntities);
        return null;
    }

    @Override
    public Student getStudent(UUID id) {
        StudentEntity result = studentRepository.findByIdAndDeleteAtIsNull(id);

        return new Student(result);
    }

    @Override
    public Message createStudent(Student data) throws CommonException {
        Message message = validate(data, false);
        if (message == null) {
            UserEntity user = new UserEntity(data);
            RoleEntity role = new RoleEntity();
            role.setId((long) 2);
            user.setRole(role);
            user.setPassword(commonService.encryptPass(user.getPassword()));
            StudentEntity student = new StudentEntity();
            student.setCreateAt(new Date());
            student.setCreateBy(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId()
            );
            student.setUser(userRepository.save(user));
            StudentEntity studentEntity = studentRepository.save(student);
            data.getClasses().forEach(item -> {
                ClassEntity classEntity = new ClassEntity(item.getId());
                StudentClassEntity studentClassEntity = new StudentClassEntity();
                studentClassEntity.setStudent(studentEntity);
                studentClassEntity.setClassroom(classEntity);
                studentClassRepository.save(studentClassEntity);
            });
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
    public Message updateStudent(Student data) throws CommonException {
        UserEntity userEntity = userRepository.findByUsername(data.getUsername());
        userEntity.getId();
        Message message = validate(data, true);
        if (message == null) {
            UserEntity user = new UserEntity(data);
            user.setId(userEntity.getId());
            if (!user.getPassword().equals("")) {
                user.setPassword(commonService.encryptPass(user.getPassword()));
            }
            userRepository.save(user);
            StudentEntity student = studentRepository.findByIdAndDeleteAtIsNull(data.getId());
            List<StudentClassEntity> studentClassEntities = studentClassRepository.getAllByStudentIdAndDeleteAtIsNull(student.getId());

            for (StudentClassEntity studentClassEntity : studentClassEntities) {
                studentClassEntity.setDeleteAt(new Date());
            }

            studentClassRepository.saveAll(studentClassEntities);
            data.getClasses().forEach(item -> {
                ClassEntity classEntity = new ClassEntity(item.getId());
                StudentClassEntity studentClassEntity = new StudentClassEntity();
                studentClassEntity.setStudent(student);
                studentClassEntity.setClassroom(classEntity);
                studentClassRepository.save(studentClassEntity);
            });
        } else {
            throw new CommonException(message);
        }
        return message;
    }

    private Message validate(Student data, Boolean isEdit) {
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
        StudentEntity student = studentRepository.findByIdAndDeleteAtIsNull(data.getId());
        if (user != null && !user.getId().equals(student.getUser().getId())) {
            message.setMgsCode("USERNAME_IS_USED");
            message.setMessage(StudentMessage.USERNAME_IS_USED.value());
        }
        System.out.println(message.getMessage() == null);
        if (message.getMessage() == null) {
            return null;
        } else return message;


    }

}

package com.main.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.main.Entities.*;
import com.main.Models.*;
import com.main.Repositories.*;
import com.main.Services.Interface.IQuestionService;
import com.main.Services.Interface.ITeachertService;
import com.main.Shared.Enums.QuestionMessage;
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
public class QuestionService implements IQuestionService {
    @Autowired
    QuestionRepository questionRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommonService commonService;

    String codePre = "CH_";


    @Override
    public PageModal<QuestionEntity> findMany(Search data) {
        Pageable pageRequest = commonService.getPage(data);

        final String[] subject = {""};
        final String[] level = {""};
        if (data.getFilters().stream().count() > 0) {
            data.getFilters().stream().forEach(item -> {
                if (item.getName().equals("subject")) {
                    subject[0] = item.getValue();
                } else if (item.getName().equals("level")) {
                    level[0] = item.getValue();
                }
            });
        }
        Page<QuestionEntity> page;
        if (subject[0].equals("") && level[0].equals("")) {
            page = questionRepository.findAllByDeleteAtIsNull(pageRequest);
        } else if (subject[0].equals("")) {
            page = questionRepository.findAllByLevelAndDeleteAtIsNull(
                    Integer.parseInt(level[0]), pageRequest
            );
        } else if (level[0].equals("")) {
            page = questionRepository.findAllBySubjectIdAndDeleteAtIsNull(
                    Long.parseLong(subject[0]), pageRequest
            );
        } else {
            page = questionRepository.findAllBySubjectIdAndLevelAndDeleteAtIsNull(
                    Long.parseLong(subject[0]), Integer.parseInt(level[0]), pageRequest
            );
        }
//        List<Teacher> teacherList = userMapper.getListTeacher(page.getContent());
        PageModal<QuestionEntity> result = new PageModal<>(page, page.getContent());
        return result;
    }

    @Override
    public QuestionEntity delete(UUID id) {
        QuestionEntity question = getQuestion(id);
        question.setDeleteAt(new Date());
        questionRepository.save(question);
        return null;
    }

    @Override
    public QuestionEntity getQuestion(UUID id) {
        QuestionEntity result = questionRepository.findByIdAndDeleteAtIsNull(id);
        result.getCode();
        return result;
    }

    @Override
    public Message createQuestion(QuestionEntity data) throws CommonException {
        Message message = validate(data, false);
        if (message == null) {
            String code = questionRepository.getQuestionCode();
            if (code != null) {
                Integer index = Integer.parseInt(code.split(this.codePre)[1]) + 1;
                String classCode = this.codePre + index;
                data.setCode(classCode);
            } else data.setCode("CH_1");
            data.setCreateAt(new Date());
            data.setCreateBy(userRepository.findByUsernameAndDeleteAtIsNull(SecurityContextHolder.getContext().getAuthentication().getName()).getId()
            );
            questionRepository.save(data);

        } else {
            throw new CommonException(message);
        }
        message = new Message();
        message.setStatus(200);
        message.setMgsCode("CREATE_SUCCESS");
        message.setMessage(QuestionMessage.CREATE_SUCCESS.value());
        return message;

    }

    @Override
    public Message updateQuestion(QuestionEntity data) throws CommonException {
        Message message = validate(data, false);
        QuestionEntity question = getQuestion(data.getId());
        if (message == null) {
            question.setUpdateAt(new Date());
            question.setUpdateBy(userRepository.findByUsernameAndDeleteAtIsNull(SecurityContextHolder.getContext().getAuthentication().getName()).getId()
            );
            question.setContent(data.getContent());
            question.setCorrectAnswer(data.getCorrectAnswer());
            question.setChoiceAnswers(data.getChoiceAnswers());
            question.setLevel(data.getLevel());
            question.setSubject(data.getSubject());
            question.setNote(data.getNote());
            questionRepository.save(question);
        } else {
            throw new CommonException(message);
        }
        message = new Message();
        message.setStatus(200);
        message.setMgsCode("UPDATE_SUCCESS");
        message.setMessage(QuestionMessage.UPDATE_SUCCESS.value());
        return message;
    }

    private Message validate(QuestionEntity data, Boolean isEdit) {
        Pattern patternMail = Pattern.compile("^[a-zA-Z0-9\\+\\.\\_%\\-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,62}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+$");
        Message message = new Message();
        message.setStatus(400);
        if (data.getContent().equals("")) {
            message.setMgsCode("CONTENT_NOT_NULL");
            message.setMessage(QuestionMessage.CONTENT_NOT_NULL.value());
        }
        if (data.getChoiceAnswers().size() == 0 || data.getChoiceAnswers().stream().anyMatch(item -> item.equals(""))) {
            message.setMgsCode("ANSWER_NOT_NULL");
            message.setMessage(QuestionMessage.ANSWER_NOT_NULL.value());
        }

        if (message.getMessage() == null) {
            return null;
        } else return message;
    }

}

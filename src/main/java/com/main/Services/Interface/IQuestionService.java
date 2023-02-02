package com.main.Services.Interface;

import com.main.Entities.QuestionEntity;
import com.main.Entities.TeacherEntity;
import com.main.Models.*;

import java.util.UUID;

public interface IQuestionService {
    PageModal<QuestionEntity> findMany(Search data);
    QuestionEntity delete(UUID id);
    QuestionEntity getQuestion(UUID id);
    Message createQuestion(QuestionEntity data) throws CommonException;
    Message updateQuestion(QuestionEntity data) throws CommonException;
}

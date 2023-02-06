package com.main.Services.Interface;

import com.main.Entities.ClassEntity;
import com.main.Entities.CompetitionEntity;
import com.main.Entities.ExamEntity;
import com.main.Entities.StudentExamEntity;
import com.main.Models.*;

import java.util.List;
import java.util.UUID;

public interface ICompetitionService {
    PageModal<Competition> findMany(Search data);
    PageModal<Competition> findManyByClass(Search data);
    CompetitionEntity delete(UUID id) throws CommonException;
    Competition get(UUID id);
    Message create(ExamForm data) throws CommonException;
    CompetitionEntity changeStatus(UUID id, Integer status) throws CommonException;
    StudentExamEntity startExam(UUID id);
    List<ExamEntity> getExams(UUID id);
    StudentExamEntity getExamByStudent(UUID id);
}

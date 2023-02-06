package com.main.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.main.Entities.*;
import com.main.Models.*;
import com.main.Repositories.*;
import com.main.Services.Interface.ICompetitionService;
import com.main.Shared.Enums.CompetitionMessage;
import com.main.Shared.Mapper.CompetitionMapper;
import com.main.Shared.Services.CommonService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CompetitionService implements ICompetitionService {
    @Autowired
    CompetitionRepository competitionRepository;
    @Autowired
    ExamRepository examRepository;
    @Autowired
    QuestionIndelibilityRepository questionIndelibilityRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentExamRepository studentExamRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CompetitionMapper competitionMapper;
    @Autowired
    CommonService commonService;

    @Override
    public PageModal<Competition> findMany(Search data) {
        Pageable pageRequest = commonService.getPage(data);

        final String[] subject = {""};
        final String[] classId = {""};
        final String[] code = {""};
        if (data.getFilters().stream().count() > 0) {
            data.getFilters().stream().forEach(item -> {
                if (item.getName().equals("subject")) {
                    subject[0] = item.getValue();
                } else if (item.getName().equals("classId")) {
                    classId[0] = item.getValue();
                } else if (item.getName().equals("code")) {
                    code[0] = item.getValue();
                }
            });
        }
        Page<CompetitionEntity> page;
        if (subject[0].equals("") && classId[0].equals("")) {
            page = competitionRepository.findAllByCodeContainsAndDeleteAtIsNull(code[0], pageRequest);
        } else if (subject[0].equals("")) {
            page = competitionRepository.findAllByCodeContainsAndClassroomIdAndDeleteAtIsNull(
                    code[0], UUID.fromString(classId[0]), pageRequest
            );
        } else if (classId[0].equals("")) {
            page = competitionRepository.findAllByCodeContainsAndSubjectIdAndDeleteAtIsNull(
                    code[0], Integer.parseInt(subject[0]), pageRequest
            );
        } else {
            page = competitionRepository.findAllByCodeContainsAndSubjectIdAndClassroomIdAndDeleteAtIsNull(
                    code[0], Integer.parseInt(subject[0]), UUID.fromString(classId[0]), pageRequest
            );
        }

        List<Competition> competitions = competitionMapper.getList(page.getContent());
        PageModal<Competition> result = new PageModal<>(page, competitions);
        return result;
    }

    @Override
    public PageModal<Competition> findManyByClass(Search data) {
        Pageable pageRequest = commonService.getPage(data);

        final String[] subject = {""};
        final String[] classId = {""};
        final String[] code = {""};
        if (data.getFilters().stream().count() > 0) {
            data.getFilters().stream().forEach(item -> {
                if (item.getName().equals("subject")) {
                    subject[0] = item.getValue();
                } else if (item.getName().equals("classId")) {
                    classId[0] = item.getValue();
                } else if (item.getName().equals("code")) {
                    code[0] = item.getValue();
                }
            });
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Page<CompetitionEntity> page;
        if (subject[0].equals("") && classId[0].equals("")) {
            page = competitionRepository.findAllByClassroomStudentsStudentUserUsernameAndCodeContainsAndDeleteAtIsNull(username, code[0], pageRequest);
        } else if (subject[0].equals("")) {
            page = competitionRepository.findAllByClassroomStudentsStudentUserUsernameAndCodeContainsAndClassroomIdAndDeleteAtIsNull(
                    username, code[0], UUID.fromString(classId[0]), pageRequest
            );
        } else if (classId[0].equals("")) {
            page = competitionRepository.findAllByClassroomStudentsStudentUserUsernameAndCodeContainsAndSubjectIdAndDeleteAtIsNull(
                    username, code[0], Integer.parseInt(subject[0]), pageRequest
            );
        } else {
            page = competitionRepository.findAllByClassroomStudentsStudentUserUsernameAndCodeContainsAndSubjectIdAndClassroomIdAndDeleteAtIsNull(
                    username, code[0], Integer.parseInt(subject[0]), UUID.fromString(classId[0]), pageRequest
            );
        }
        List<UUID> ids = page.getContent().stream().map(item -> item.getId()).toList();
        Map<UUID, Integer> degrees = studentExamRepository.getDegree(ids);
        List<Competition> competitions = competitionMapper.getList(page.getContent(), degrees);
        PageModal<Competition> result = new PageModal<>(page, competitions);
        return result;
    }


    @Override
    public CompetitionEntity delete(UUID id) throws CommonException {
        CompetitionEntity result = competitionRepository.findByIdAndDeleteAtIsNull(id);
        if (result.getStatus() == 0) {
            result.setDeleteAt(new Date());
            competitionRepository.save(result);
            return null;
        } else {
            Message message = new Message();
            message.setStatus(400);
            message.setMessage("Bài thi này không được xóa.");
            throw new CommonException(message);
        }
    }

    @Override
    public CompetitionEntity changeStatus(UUID id, Integer status) throws CommonException {
        CompetitionEntity result = competitionRepository.findByIdAndDeleteAtIsNull(id);
        if (result.getStatus() != 2) {
            result.setStatus(status);
            competitionRepository.save(result);
            return null;
        } else {
            Message message = new Message();
            message.setStatus(400);
            message.setMessage("Bài thi này không được xóa.");
            throw new CommonException(message);
        }
    }

    @Override
    public Competition get(UUID id) {
        CompetitionEntity result = competitionRepository.findByIdAndDeleteAtIsNull(id);
        return new Competition(result);
    }

    @Override
    public Message create(ExamForm data) throws CommonException {
        Message message = validate(data);
        if (message == null) {
            CompetitionEntity competitionEntity = new CompetitionEntity(data);
            competitionEntity.setCreateAt(new Date());
            competitionEntity.setCreateBy(userRepository.findByUsernameAndDeleteAtIsNull(SecurityContextHolder.getContext().getAuthentication().getName()).getId()
            );
            CompetitionEntity competition = competitionRepository.save(competitionEntity);
            for (int i = 0; i < data.getNumberOfExam(); i++) {
                ExamEntity examEntity = new ExamEntity();
                examEntity.setCode(RandomStringUtils.random(5, true, true));
                examEntity.setCompetition(competition);
                if (data.getHasQuestionLv1()) {
                    examEntity.setQuestionLv1(randomQuestion(1, data.getNumberOfQuestionLv1()));
                }
                if (data.getHasQuestionLv2()) {
                    examEntity.setQuestionLv2(randomQuestion(2, data.getNumberOfQuestionLv2()));
                }
                if (data.getHasQuestionLv3()) {
                    examEntity.setQuestionLv3(randomQuestion(3, data.getNumberOfQuestionLv3()));
                }
                if (data.getHasQuestionLv4()) {
                    examEntity.setQuestionLv4(randomQuestion(4, data.getNumberOfQuestionLv4()));
                }
                examRepository.save(examEntity);
            }
        } else {
            throw new CommonException(message);
        }
        message = new Message();
        message.setStatus(200);
        message.setMgsCode("CREATE_SUCCESS");
        message.setMessage(CompetitionMessage.CREATE_SUCCESS.value());
        return message;
    }

    @Override
    public List<ExamEntity> getExams(UUID id) {
        return examRepository.findByCompetitionId(id);
    }

    @Override
    public StudentExamEntity getExamByStudent(UUID id) {
        StudentExamEntity studentExamEntity = studentExamRepository.getByStudentUserUsernameAndExamCompetitionId(SecurityContextHolder.getContext().getAuthentication().getName(), id);
        try {
            studentExamEntity.getExam().setQuestionLv1(studentExamEntity.getExam().getQuestionLv1().stream().map(item -> {
                item.setCorrectAnswer(null);
                return item;
            }).toList());
            studentExamEntity.getExam().setQuestionLv2(   studentExamEntity.getExam().getQuestionLv2().stream().map(item -> {
                item.setCorrectAnswer(null);
                return item;
            }).toList());
            studentExamEntity.getExam().setQuestionLv3(     studentExamEntity.getExam().getQuestionLv3().stream().map(item -> {
                item.setCorrectAnswer(null);
                return item;
            }).toList());
            studentExamEntity.getExam().setQuestionLv4(     studentExamEntity.getExam().getQuestionLv4().stream().map(item -> {
                item.setCorrectAnswer(null);
                return item;
            }).toList());
            return studentExamEntity;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public StudentExamEntity startExam(UUID id) {
        ExamEntity exam = examRepository.findById(commonService.convertBytesToUUID(examRepository.getRandomId(id)));
        StudentExamEntity studentExamEntity = new StudentExamEntity();
        studentExamEntity.setExam(exam);
        studentExamEntity.setStudent(studentRepository.findByUserUsernameAndDeleteAtIsNull(SecurityContextHolder.getContext().getAuthentication().getName()));
        studentExamEntity.setStatus(0);
        studentExamEntity.setStartAt(new Date());
        return studentExamRepository.save(studentExamEntity);
    }

    private Message validate(ExamForm data) {
        Message message = new Message();
        message.setStatus(400);
        if (data.getCode().equals("")) {
            message.setMgsCode("CODE_NOT_NULL");
            message.setMessage(CompetitionMessage.CODE_NOT_NULL.value());
        } else if (data.getCode().length() > 100) {
            message.setMgsCode("CODE_MAX_LENGTH");
            message.setMessage(CompetitionMessage.CODE_MAX_LENGTH.value());
        } else if (data.getTitle().length() > 100) {
            message.setMgsCode("TITLE_NOT_NULL");
            message.setMessage(CompetitionMessage.TITLE_NOT_NULL.value());
        } else if (data.getTitle().length() > 255) {
            message.setMgsCode("TITLE_MAX_LENGTH");
            message.setMessage(CompetitionMessage.TITLE_MAX_LENGTH.value());
        } else if (data.getNumberOfExam() < 1) {
            message.setMgsCode("EXAM_NUMBER_MIN");
            message.setMessage(CompetitionMessage.EXAM_NUMBER_MIN.value());
        } else if (data.getNumberOfExam() > 100) {
            message.setMgsCode("EXAM_NUMBER_MAX");
            message.setMessage(CompetitionMessage.EXAM_NUMBER_MAX.value());
        } else if (data.getHasQuestionLv1() && data.getNumberOfQuestionLv1() < 1) {
            message.setMgsCode("QUESTION_NUMBER_MIN");
            message.setMessage(CompetitionMessage.QUESTION_NUMBER_MIN.value());
        } else if (data.getHasQuestionLv1() && data.getNumberOfQuestionLv1() > 100) {
            message.setMgsCode("QUESTION_NUMBER_MAX");
            message.setMessage(CompetitionMessage.QUESTION_NUMBER_MAX.value());
        } else if (data.getHasQuestionLv2() && data.getNumberOfQuestionLv2() < 1) {
            message.setMgsCode("QUESTION_NUMBER_MIN");
            message.setMessage(CompetitionMessage.QUESTION_NUMBER_MIN.value());
        } else if (data.getHasQuestionLv2() && data.getNumberOfQuestionLv2() > 100) {
            message.setMgsCode("QUESTION_NUMBER_MAX");
            message.setMessage(CompetitionMessage.QUESTION_NUMBER_MAX.value());
        } else if (data.getHasQuestionLv3() && data.getNumberOfQuestionLv3() < 1) {
            message.setMgsCode("QUESTION_NUMBER_MIN");
            message.setMessage(CompetitionMessage.QUESTION_NUMBER_MIN.value());
        } else if (data.getHasQuestionLv3() && data.getNumberOfQuestionLv3() > 100) {
            message.setMgsCode("QUESTION_NUMBER_MAX");
            message.setMessage(CompetitionMessage.QUESTION_NUMBER_MAX.value());
        } else if (data.getHasQuestionLv4() && data.getNumberOfQuestionLv4() < 1) {
            message.setMgsCode("QUESTION_NUMBER_MIN");
            message.setMessage(CompetitionMessage.QUESTION_NUMBER_MIN.value());
        } else if (data.getHasQuestionLv4() && data.getNumberOfQuestionLv4() > 100) {
            message.setMgsCode("QUESTION_NUMBER_MAX");
            message.setMessage(CompetitionMessage.QUESTION_NUMBER_MAX.value());
        }

        if (message.getMessage() == null) {
            return null;
        } else return message;
    }

    public List<QuestionIndelibilityEntity> randomQuestion(Integer level, Integer length) {
        Integer qty = questionRepository.countAllByLevelAndDeleteAtIsNull(level);
        List<Integer> range = IntStream.rangeClosed(1, qty)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(range);
        List<Integer> randomSeries = range.subList(0, length + 1);
        List<QuestionIndelibilityEntity> questionIndelibilityEntities = new ArrayList<>();
        randomSeries.forEach(item -> {
            Page<QuestionEntity> questionPage = questionRepository.findAllByLevelAndDeleteAtIsNull(level, PageRequest.of(item, 1));
            QuestionEntity q = null;
            if (questionPage.hasContent()) {
                q = questionPage.getContent().get(0);
                questionIndelibilityEntities.add(new QuestionIndelibilityEntity(q));
            }
        });
        try {
            System.out.println(commonService.convertObjectToJson(questionIndelibilityEntities));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return questionIndelibilityRepository.saveAll(questionIndelibilityEntities);
    }
}

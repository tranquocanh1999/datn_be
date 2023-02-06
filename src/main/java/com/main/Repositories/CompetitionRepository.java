package com.main.Repositories;

import com.main.Entities.ClassEntity;
import com.main.Entities.CompetitionEntity;
import com.main.Entities.ExamEntity;
import com.main.Entities.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long> {
    Page<CompetitionEntity> findAllByCodeContainsAndDeleteAtIsNull(String code, Pageable pageable);

    Page<CompetitionEntity> findAllByCodeContainsAndSubjectIdAndDeleteAtIsNull(String code, Integer subjectId, Pageable pageable);

    Page<CompetitionEntity> findAllByCodeContainsAndClassroomIdAndDeleteAtIsNull(String code, UUID classId, Pageable pageable);

    Page<CompetitionEntity> findAllByCodeContainsAndSubjectIdAndClassroomIdAndDeleteAtIsNull(String code, Integer subjectId, UUID classId, Pageable pageable);

    CompetitionEntity findByIdAndDeleteAtIsNull(UUID id);

    Page<CompetitionEntity> findAllByClassroomStudentsStudentUserUsernameAndCodeContainsAndDeleteAtIsNull(String username, String code, Pageable pageable);

    Page<CompetitionEntity> findAllByClassroomStudentsStudentUserUsernameAndCodeContainsAndSubjectIdAndDeleteAtIsNull(String username, String code, Integer subjectId, Pageable pageable);

    Page<CompetitionEntity> findAllByClassroomStudentsStudentUserUsernameAndCodeContainsAndClassroomIdAndDeleteAtIsNull(String username, String code, UUID classId, Pageable pageable);

    Page<CompetitionEntity> findAllByClassroomStudentsStudentUserUsernameAndCodeContainsAndSubjectIdAndClassroomIdAndDeleteAtIsNull(String username, String code, Integer subjectId, UUID classId, Pageable pageable);

}

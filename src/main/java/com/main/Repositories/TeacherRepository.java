package com.main.Repositories;

import com.main.Entities.StudentEntity;
import com.main.Entities.TeacherEntity;
import com.main.Entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Page<TeacherEntity> findAllByUserFullNameContainsAndUserUsernameContainsAndClassesClassroomIdAndDeleteAtIsNull(String fullName, String username, UUID classID, Pageable pageable);
    Page<TeacherEntity> findAllByUserFullNameContainsAndUserUsernameContainsAndSubjectsSubjectIdAndDeleteAtIsNull(String fullName, String username, Long subjectID, Pageable pageable);
    Page<TeacherEntity> findAllByUserFullNameContainsAndUserUsernameContainsAndClassesClassroomIdAndSubjectsSubjectIdAndDeleteAtIsNull(String fullName, String username, UUID classID, Long subjectID, Pageable pageable);

    Page<TeacherEntity> findAllByUserFullNameContainsAndUserUsernameContainsAndDeleteAtIsNull(String fullName, String username, Pageable pageable);
    TeacherEntity findByIdAndDeleteAtIsNull(UUID id);
    TeacherEntity findById(UUID id);
}

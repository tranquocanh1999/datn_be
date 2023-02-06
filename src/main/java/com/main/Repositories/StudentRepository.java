package com.main.Repositories;

import com.main.Entities.ClassEntity;
import com.main.Entities.StudentEntity;
import com.main.Entities.TeacherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Page<StudentEntity> findAllByUserFullNameContainsAndUserUsernameContainsAndClassesClassroomIdAndDeleteAtIsNull(String fullName, String username, UUID classID, Pageable pageable);

    Page<StudentEntity> findAllByUserFullNameContainsAndUserUsernameContainsAndDeleteAtIsNull(String fullName, String username, Pageable pageable);
    StudentEntity findByIdAndDeleteAtIsNull(UUID id);
    StudentEntity findByUserUsernameAndDeleteAtIsNull(String username);

    StudentEntity findById(UUID id);
}

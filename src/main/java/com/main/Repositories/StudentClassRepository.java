package com.main.Repositories;

import com.main.Entities.StudentClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentClassRepository  extends JpaRepository<StudentClassEntity, Long> {
    List<StudentClassEntity> getAllByStudentIdAndDeleteAtIsNull(UUID studentID);
    List<StudentClassEntity> getAllByClassroomId(UUID classID);
}

package com.main.Repositories;

import com.main.Entities.StudentClassEntity;
import com.main.Entities.TeacherClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherClassRepository extends JpaRepository<TeacherClassEntity, Long> {
    List<TeacherClassEntity> getAllByTeacherIdAndDeleteAtIsNull(UUID id);
    List<TeacherClassEntity> getAllByClassroomId(UUID id);
}

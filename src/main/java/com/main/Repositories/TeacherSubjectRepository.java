package com.main.Repositories;

import com.main.Entities.TeacherClassEntity;
import com.main.Entities.TeacherSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherSubjectRepository extends JpaRepository<TeacherSubjectEntity, Long> {
    List<TeacherSubjectEntity> getAllByTeacherIdAndDeleteAtIsNull(UUID id);
    List<TeacherSubjectEntity> getAllBySubjectId(UUID id);
}

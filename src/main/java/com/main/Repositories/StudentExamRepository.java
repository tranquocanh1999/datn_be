package com.main.Repositories;

import com.main.Entities.StudentExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface StudentExamRepository extends JpaRepository<StudentExamEntity, Long> {
    @Query(value = "SELECT exam_id,degree FROM datn_db.user_exam where exam_id IN (:ids)", nativeQuery = true)
    Map<UUID,Integer> getDegree(List<UUID> ids);

    StudentExamEntity getByStudentUserUsernameAndExamCompetitionId(String username,UUID id);
}

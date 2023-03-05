package com.main.Repositories;

import com.main.Entities.ClassEntity;
import com.main.Entities.ExamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
    List<ExamEntity> findByCompetitionId(UUID id);

    @Query(value = "SELECT e.id FROM giáo viênexam e left join giáo viêncompetition c ON e.competition_id = c.id  where c.id= :id order by RAND() limit 0,1", nativeQuery = true)
    byte[] getRandomId(UUID id);
    ExamEntity findById(UUID id);
}

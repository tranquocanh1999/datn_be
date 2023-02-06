package com.main.Repositories;

import com.main.Entities.ClassEntity;
import com.main.Entities.QuestionEntity;
import com.main.Entities.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    Page<QuestionEntity> findAllByDeleteAtIsNull(Pageable pageable);

    Page<QuestionEntity> findAllBySubjectIdAndDeleteAtIsNull(Long id, Pageable pageable);

    Page<QuestionEntity> findAllByLevelAndDeleteAtIsNull(Integer level, Pageable pageable);

    Page<QuestionEntity> findAllBySubjectIdAndLevelAndDeleteAtIsNull(Long id, Integer level, Pageable pageable);

    QuestionEntity findByIdAndDeleteAtIsNull(UUID id);

    Integer countAllByLevelAndDeleteAtIsNull(Integer Level);
    @Query(value = "SELECT code FROM datn_db.question order by create_at desc limit 0,1", nativeQuery = true)
    String getQuestionCode();

}

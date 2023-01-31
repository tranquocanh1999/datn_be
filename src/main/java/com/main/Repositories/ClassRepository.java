package com.main.Repositories;

import com.main.Entities.ClassEntity;
import com.main.Models.ClassModel2;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    Page<ClassEntity> findAllByClassCodeContainsAndClassNameContainsAndDeleteAtIsNull(String classCode, String className, Pageable pageable);

    ClassEntity findByIdAndDeleteAtIsNull(UUID id);

    @Query(value = "SELECT class_code FROM datn_db.class order by create_at desc limit 0,1", nativeQuery = true)
    String getClassCode();


    List<ClassEntity> findAllByDeleteAtIsNull();
}

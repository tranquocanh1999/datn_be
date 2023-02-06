package com.main.Repositories;

import com.main.Entities.QuestionIndelibilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionIndelibilityRepository extends JpaRepository<QuestionIndelibilityEntity, Long> {

}

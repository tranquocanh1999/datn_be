package com.main.Shared.Mapper;

import com.main.Entities.ClassEntity;
import com.main.Entities.QuestionEntity;
import com.main.Entities.QuestionIndelibilityEntity;
import com.main.Models.ClassModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionMapper {
    public List<QuestionIndelibilityEntity> getListQuestion(List<QuestionEntity> questionEntities) {
        List<QuestionIndelibilityEntity> questionIndelibilityEntities = new ArrayList<>();
        questionEntities.forEach(item -> {
            questionIndelibilityEntities.add(new QuestionIndelibilityEntity(item));
        });
        return questionIndelibilityEntities;
    }
}
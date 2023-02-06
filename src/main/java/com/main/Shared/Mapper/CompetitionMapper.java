package com.main.Shared.Mapper;

import com.main.Entities.CompetitionEntity;
import com.main.Entities.QuestionEntity;
import com.main.Entities.QuestionIndelibilityEntity;
import com.main.Models.Competition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CompetitionMapper {
    public List<Competition> getList(List<CompetitionEntity> competitionEntities) {
        List<Competition> competitions = new ArrayList<>();
        competitionEntities.forEach(item -> {
            competitions.add(new Competition(item));
        });
        return competitions;
    }

    public List<Competition> getList(List<CompetitionEntity> competitionEntities, Map<UUID, Integer> degrees) {
        List<Competition> competitions = new ArrayList<>();
        competitionEntities.forEach(item -> {
            Competition competition = new Competition(item);
            if (degrees.get(item.getId()) != null) {
                competition.setDegree(degrees.get(item.getId()));
            } else {
                competition.setDegree(0);
            }
            competitions.add(competition);
        });
        return competitions;
    }
}

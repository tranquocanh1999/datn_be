package com.main.Shared.Mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.main.Entities.CompetitionEntity;
import com.main.Models.Competition;
import com.main.Models.DegreeGraph;
import com.main.Models.StudentExam;
import com.main.Shared.Services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CompetitionMapper {
    @Autowired
    CommonService commonService;

    public List<Competition> getList(List<CompetitionEntity> competitionEntities) {
        List<Competition> competitions = new ArrayList<>();
        competitionEntities.forEach(item -> {
            competitions.add(new Competition(item));
        });
        return competitions;
    }

    public List<Competition> getList(List<CompetitionEntity> competitionEntities, List<Object[]> degrees) {
        List<Competition> competitions = new ArrayList<>();
        competitionEntities.forEach(item -> {
            Competition competition = new Competition(item);
            competition.setDegree(Double.parseDouble("0"));
            degrees.stream().forEach(degree -> {
                        if (commonService.convertBytesToUUID((byte[]) degree[0]).equals(item.getId())) {
                           competition.setDegree((double) degree[1]);
                        }
                    }
            );

            competitions.add(competition);
        });
        return competitions;
    }

    public List<StudentExam> getListStudent(List<List<String>> students) {
        List<StudentExam> studentExams = new ArrayList<>();
        students.forEach(item -> {
            studentExams.add(new StudentExam(item));
        });
        return studentExams;
    }

    public List<DegreeGraph> getDataDegree(List<List<String>> degrees) {
        List<DegreeGraph> degreeGraphs = new ArrayList<>();
        degrees.forEach(item -> {
            degreeGraphs.add(new DegreeGraph(item));
        });
        return degreeGraphs;
    }
}

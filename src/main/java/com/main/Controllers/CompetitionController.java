package com.main.Controllers;


import com.main.Entities.QuestionEntity;
import com.main.Models.CommonException;
import com.main.Models.ExamForm;
import com.main.Models.Message;
import com.main.Models.Search;
import com.main.Services.Interface.ICompetitionService;
import com.main.Services.Interface.IQuestionService;
import com.main.Shared.Enums.ClassMessage;
import com.main.Shared.Enums.CommonMessage;
import com.main.Shared.Enums.CompetitionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Autowired
    private ICompetitionService competitionService;

    @PostMapping("/list")
    public ResponseEntity getCompetitions(@RequestBody Search data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(competitionService.findMany(data));
        } catch (Exception e) {
            message = new Message(
                    "SERVER_ERROR",
                    CommonMessage.SERVER_ERROR.value(),
                    500);
        }
        return new ResponseEntity<Object>(
                message, new HttpHeaders(), message.getStatus());
    }

    @PostMapping("")
    public ResponseEntity createQuestion(@RequestBody ExamForm data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(competitionService.create(data));
        } catch (CommonException e) {
            message = e.getData();
        } catch (Exception e) {
            message = new Message(
                    "SERVER_ERROR",
                    CommonMessage.SERVER_ERROR.value(),
                    500);
        }
        return new ResponseEntity<Object>(
                message, new HttpHeaders(), message.getStatus());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteCompetition(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            competitionService.delete(id);
            message = new Message(
                    "DELETE_SUCCESS",
                    CompetitionMessage.DELETE_SUCCESS.value(),
                    200);

        } catch (NullPointerException e) {
            message = new Message(
                    "CLASS_NOT_EXIST",
                    CompetitionMessage.COMPETITION_NOT_EXIST.value(),
                    400);
        } catch (Exception e) {

            message = new Message(
                    "SERVER_ERROR",
                    CommonMessage.SERVER_ERROR.value(),
                    500);
        }
        return new ResponseEntity<Object>(
                message, new HttpHeaders(), message.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCompetition(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(competitionService.get(id));
        } catch (NullPointerException e) {
            message = new Message(
                    "COMPETITION_NOT_EXIST",
                    CompetitionMessage.COMPETITION_NOT_EXIST.value(),
                    400);
        } catch (Exception e) {
            message = new Message(
                    "SERVER_ERROR",
                    CommonMessage.SERVER_ERROR.value(),
                    500);
        }
        return new ResponseEntity<Object>(
                message, new HttpHeaders(), message.getStatus());
    }

    @GetMapping("/exams/{id}")
    public ResponseEntity getExams(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(competitionService.getExams(id));
        } catch (NullPointerException e) {
            message = new Message(
                    "COMPETITION_NOT_EXIST",
                    CompetitionMessage.COMPETITION_NOT_EXIST.value(),
                    400);
        } catch (Exception e) {
            message = new Message(
                    "SERVER_ERROR",
                    CommonMessage.SERVER_ERROR.value(),
                    500);
        }
        return new ResponseEntity<Object>(
                message, new HttpHeaders(), message.getStatus());
    }

    @GetMapping("/exams/status/{id}")
    public ResponseEntity changeStatus(@PathVariable UUID id, @RequestParam Integer status) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(competitionService.changeStatus(id,status));
        } catch (NullPointerException e) {
            message = new Message(
                    "COMPETITION_NOT_EXIST",
                    CompetitionMessage.COMPETITION_NOT_EXIST.value(),
                    400);
        } catch (Exception e) {

            message = new Message(
                    "SERVER_ERROR",
                    CommonMessage.SERVER_ERROR.value(),
                    500);
        }
        return new ResponseEntity<Object>(
                message, new HttpHeaders(), message.getStatus());
    }

}

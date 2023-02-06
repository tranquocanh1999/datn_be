package com.main.Controllers;

import com.main.Models.Message;
import com.main.Models.Search;
import com.main.Services.Interface.IClassService;
import com.main.Services.Interface.ICompetitionService;
import com.main.Shared.Enums.CommonMessage;
import com.main.Shared.Enums.CompetitionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user-exam")
public class UerExamController {
    @Autowired
    IClassService classService;

    @Autowired
    private ICompetitionService competitionService;

    @PostMapping("/list")
    public ResponseEntity getCompetitions(@RequestBody Search data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(competitionService.findManyByClass(data));
        } catch (Exception e) {
            System.out.println(e);
            message = new Message(
                    "SERVER_ERROR",
                    CommonMessage.SERVER_ERROR.value(),
                    500);
        }
        return new ResponseEntity<Object>(
                message, new HttpHeaders(), message.getStatus());
    }

    @PostMapping("/class/list")
    public ResponseEntity getClasses() throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(classService.getAllByStudent());
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
    public ResponseEntity getExamByStudent(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(competitionService.getExamByStudent(id));
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

    @GetMapping("/exam/start/{id}")
    public ResponseEntity startExam(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(competitionService.startExam(id));
        } catch (NullPointerException e) {
            message = new Message(
                    "COMPETITION_NOT_EXIST",
                    CompetitionMessage.COMPETITION_NOT_EXIST.value(),
                    400);
        } catch (Exception e) {
            System.out.println(e);
            message = new Message(
                    "SERVER_ERROR",
                    CommonMessage.SERVER_ERROR.value(),
                    500);
        }
        return new ResponseEntity<Object>(
                message, new HttpHeaders(), message.getStatus());
    }
}

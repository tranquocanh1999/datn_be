package com.main.Controllers;

import com.main.Entities.QuestionEntity;
import com.main.Models.CommonException;
import com.main.Models.Message;
import com.main.Models.Search;
import com.main.Models.Teacher;
import com.main.Services.Interface.IQuestionService;
import com.main.Services.Interface.ITeachertService;
import com.main.Shared.Enums.CommonMessage;
import com.main.Shared.Enums.StudentMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/question")

public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @PostMapping("/list")
    public ResponseEntity getQuestions(@RequestBody Search data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(questionService.findMany(data));
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
    public ResponseEntity delete(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            questionService.delete(id);
            message = new Message(
                    "DELETE_SUCCESS",
                    StudentMessage.DELETE_SUCCESS.value(),
                    200);

        } catch (NullPointerException e) {
            message = new Message(
                    "STUDENT_NOT_EXIST",
                    StudentMessage.STUDENT_NOT_EXIST.value(),
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
    public ResponseEntity getStudent(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(questionService.getQuestion(id));
        } catch (NullPointerException e) {
            message = new Message(
                    "STUDENT_NOT_EXIST",
                    StudentMessage.STUDENT_NOT_EXIST.value(),
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

    @PostMapping("")
    public ResponseEntity createQuestion(@RequestBody QuestionEntity data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(questionService.createQuestion(data));
        } catch (CommonException e) {
            message = e.getData();
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

    @PostMapping("/{id}")
    public ResponseEntity updateQuestion(@RequestBody QuestionEntity data, @PathVariable UUID id) throws Exception {
        Message message ;
        try {
            data.setId(id);
            return ResponseEntity.ok(questionService.updateQuestion(data));
        } catch (CommonException e) {
            message = e.getData();
        }  catch (NullPointerException e) {
            message = new Message(
                    "STUDENT_NOT_EXIST",
                    StudentMessage.STUDENT_NOT_EXIST.value(),
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

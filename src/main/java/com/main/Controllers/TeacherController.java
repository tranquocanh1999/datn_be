package com.main.Controllers;

import com.main.Models.*;
import com.main.Services.Interface.ITeachertService;
import com.main.Shared.Enums.CommonMessage;
import com.main.Shared.Enums.StudentMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/teacher")

public class TeacherController {
    @Autowired
    private ITeachertService teachertService;

    @PostMapping("/list")
    public ResponseEntity getTeachers(@RequestBody Search data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(teachertService.findMany(data));
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
            teachertService.delete(id);
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
    public ResponseEntity getTeacher(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(teachertService.getTeacher(id));
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
    public ResponseEntity createTeacher(@RequestBody Teacher data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(teachertService.createTeacher(data));
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

    @PostMapping("/{id}")
    public ResponseEntity updateTeacher(@RequestBody Teacher data, @PathVariable UUID id) throws Exception {
        Message message ;
        try {
            data.setId(id);
            return ResponseEntity.ok(teachertService.updateTeacher(data));
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

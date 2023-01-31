package com.main.Controllers;

import com.main.Entities.ClassEntity;
import com.main.Models.CommonException;
import com.main.Models.Message;
import com.main.Models.Search;
import com.main.Models.Student;
import com.main.Services.Interface.IStudentService;
import com.main.Shared.Enums.ClassMessage;
import com.main.Shared.Enums.CommonMessage;
import com.main.Shared.Enums.StudentMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;



@RestController
@RequestMapping("/student")

public class StudentController {
    @Autowired
    private IStudentService studentService;

    @PostMapping("/list")
    public ResponseEntity getClasses(@RequestBody Search data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(studentService.findMany(data));
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

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            studentService.delete(id);
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
            return ResponseEntity.ok(studentService.getStudent(id));
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
    public ResponseEntity createStudent(@RequestBody Student data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(studentService.createStudent(data));
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
    public ResponseEntity updateStudent(@RequestBody Student data, @PathVariable UUID id) throws Exception {
        Message message ;
        try {
            data.setId(id);
            return ResponseEntity.ok(studentService.updateStudent(data));
        } catch (CommonException e) {
            message = e.getData();
        }  catch (NullPointerException e) {
            message = new Message(
                    "STUDENT_NOT_EXIST",
                    StudentMessage.STUDENT_NOT_EXIST.value(),
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

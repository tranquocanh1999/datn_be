package com.main.Controllers;

import com.main.Entities.ClassEntity;
import com.main.Models.CommonException;
import com.main.Models.Message;
import com.main.Models.Search;
import com.main.Services.Interface.IClassService;
import com.main.Shared.Enums.ClassMessage;
import com.main.Shared.Enums.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/class")

public class ClassController {
    @Autowired
    private IClassService classService;

    @PostMapping("/list")
    public ResponseEntity getClasses(@RequestBody Search data) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(classService.findMany(data));
        } catch (Exception e) {

            message = new Message(
                    "SERVER_ERROR",
                    CommonMessage.SERVER_ERROR.value(),
                    500);
        }
        return new ResponseEntity<Object>(
                message, new HttpHeaders(), message.getStatus());
    }

    @GetMapping("/all")
    public ResponseEntity getAll() throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(classService.getAll());
        } catch (NullPointerException e) {
            message = new Message(
                    "CLASS_NOT_EXIST",
                    ClassMessage.CLASS_NOT_EXIST.value(),
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

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteClass(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            classService.delete(id);
            message = new Message(
                    "DELETE_SUCCESS",
                    ClassMessage.DELETE_SUCCESS.value(),
                    200);

        } catch (NullPointerException e) {
            message = new Message(
                    "CLASS_NOT_EXIST",
                    ClassMessage.CLASS_NOT_EXIST.value(),
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
    public ResponseEntity getClass(@PathVariable UUID id) throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(classService.getClass(id));
        } catch (NullPointerException e) {
            message = new Message(
                    "CLASS_NOT_EXIST",
                    ClassMessage.CLASS_NOT_EXIST.value(),
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
    public ResponseEntity createClass(@RequestBody ClassEntity data) throws Exception {
        Message message;
        try {

            return ResponseEntity.ok(classService.createClass(data));
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
    public ResponseEntity updateClass(@RequestBody ClassEntity data, @PathVariable UUID id) throws Exception {
        Message message;
        try {
            data.setId(id);
            return ResponseEntity.ok(classService.updateClass(data));
        } catch (NullPointerException e) {
            message = new Message(
                    "CLASS_NOT_EXIST",
                    ClassMessage.CLASS_NOT_EXIST.value(),
                    400);
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

}

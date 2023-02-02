package com.main.Controllers;

import com.main.Entities.ClassEntity;
import com.main.Models.CommonException;
import com.main.Models.Message;
import com.main.Models.Search;
import com.main.Services.Interface.IClassService;
import com.main.Services.Interface.ISubjectService;
import com.main.Shared.Enums.ClassMessage;
import com.main.Shared.Enums.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subject")

public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/list")
    public ResponseEntity getAll() throws Exception {
        Message message;
        try {
            return ResponseEntity.ok(subjectService.getAll());
        } catch (NullPointerException e) {
            message = new Message(
                    "CLASS_NOT_EXIST",
                    ClassMessage.CLASS_NOT_EXIST.value(),
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

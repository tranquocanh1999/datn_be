package com.main.Controllers;

import com.main.Models.*;
import com.main.Services.AuthService;
import com.main.Shared.Enums.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @GetMapping("/login")
    public ResponseEntity Login(@RequestParam String username, @RequestParam String password) throws Exception {
        try {
            Token token = authService.Login(username, password);
            return ResponseEntity.ok(token);
        } catch (NullPointerException e) {
            Message message = new Message(
                    "LOGIN_FAILED",
                    CommonMessage.LOGIN_FAILED.value(),
                    400);
            return new ResponseEntity<Object>(
                    message, new HttpHeaders(), message.getStatus());
        }

    }

    @GetMapping("/info")
    public ResponseEntity getUser() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try {

            Teacher teacher = authService.LoadTeacherByUsername(username);
            if (teacher.getId() != null) {
                return ResponseEntity.ok(teacher);
            }
            Student student = authService.LoadStudentByUsername(username);
            return ResponseEntity.ok(student);
        } catch (NullPointerException e) {

            Message message = new Message(
                    "LOGIN_FAILED",
                    CommonMessage.LOGIN_FAILED.value(),
                    400);
            return new ResponseEntity<Object>(
                    message, new HttpHeaders(), message.getStatus());

        }

    }

    @GetMapping("/token")
    public ResponseEntity getToken(@RequestParam String refreshToken) {
        try {
            Token token = authService.GetToken(refreshToken);
            return ResponseEntity.ok(token);
        } catch (CommonException e) {
            Message message = e.getData();
            return new ResponseEntity<Object>(
                    message, new HttpHeaders(), message.getStatus());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestParam UUID tokenID) {
        try {
            authService.Logout(tokenID);
            return ResponseEntity.ok(new Message(
                    "LOGOUT_SUCCESS",
                    CommonMessage.LOGOUT_SUCCESS.value(),
                    200));
        } catch (CommonException e) {
            Message message = e.getData();
            return new ResponseEntity<Object>(
                    message, new HttpHeaders(), message.getStatus());
        }
    }
}

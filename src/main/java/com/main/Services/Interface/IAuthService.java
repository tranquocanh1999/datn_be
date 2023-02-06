package com.main.Services.Interface;

import com.main.Models.CommonException;
import com.main.Models.Student;
import com.main.Models.Teacher;
import com.main.Models.Token;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IAuthService {
    Token Login(String username, String password);
    Void Logout(UUID tokenID) throws CommonException;
    Token GetToken(String refreshToken) throws CommonException;
    UserDetails LoadUserByUsername(String username);
    Teacher LoadTeacherByUsername(String username);

    Student LoadStudentByUsername(String username);

}

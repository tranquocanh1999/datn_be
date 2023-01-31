package com.main.Services.Interface;

import com.main.Models.CommonException;
import com.main.Models.Token;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IAuthService {
    Token Login(String username, String password);
    Void Logout(UUID tokenID) throws CommonException;
    Token GetToken(String refreshToken) throws CommonException;
    UserDetails LoadUserByUsername(String username);

}

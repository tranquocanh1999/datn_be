package com.main.Services;


import com.main.Entities.*;
import com.main.Models.CommonException;
import com.main.Models.Message;
import com.main.Models.Token;
import com.main.Repositories.*;
import com.main.Services.Interface.IAuthService;
import com.main.Shared.Enums.CommonMessage;
import com.main.Shared.Services.CommonService;
import com.main.config.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AuthService implements IAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private CommonService commonService;

    @Autowired
    private TokenRepository tokenRepository;


    public Token Login(String username, String password) {

        UserEntity user = userRepository.findByUsernameAndPassword(username, commonService.encryptPass(password));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
                , true, true, true,
                true, getAuthorities(user.getRole()));
        TokenEntity tokenEntity = new TokenEntity();

        String accessToken = jwtTokenService.generateAccessToken(userDetails);
        String refreshToken = jwtTokenService.generateRefreshToken(userDetails);
        tokenEntity.setAccessToken(accessToken);
        tokenEntity.setRefreshToken(refreshToken);
        tokenEntity.setUser(user);

        tokenRepository.save(tokenEntity);
        return new Token(tokenEntity);
    }

    @Override
    public Void Logout(UUID tokenID) throws CommonException {
        TokenEntity tokenEntity = tokenRepository.getByIdAndDeleteAtIsNull(tokenID);
        if(Objects.isNull(tokenEntity)){
            throw new CommonException(new Message(
                    "NO_TOKEN",
                    CommonMessage.NO_TOKEN.value(),
                    401));
        }
        tokenEntity.setDeleteAt(new Date());
        tokenRepository.save(tokenEntity);
        return null;
    }

    @Override
    public Token GetToken(String refreshToken) throws CommonException {
        TokenEntity tokenEntity = tokenRepository.getByRefreshTokenAndDeleteAtIsNull(refreshToken);
        if(Objects.isNull(tokenEntity)){
            throw new CommonException(new Message(
                    "NO_TOKEN",
                    CommonMessage.NO_TOKEN.value(),
                    401));
        }
        UserEntity user = tokenEntity.getUser();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
                , true, true, true,
                true, getAuthorities(user.getRole()));

        if (jwtTokenService.isTokenExpired(refreshToken)) {
            throw new CommonException(new Message(
                    "TOKEN_EXPIRED",
                    CommonMessage.TOKEN_EXPIRED.value(),
                    401));
        }
        String accessToken = jwtTokenService.generateAccessToken(userDetails);
        tokenEntity.setAccessToken(accessToken);
        tokenRepository.save(tokenEntity);
        return new Token(tokenEntity);
    }

    public UserDetails LoadUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
                , true, true, true,
                true, getAuthorities(user.getRole()));
    }


    public Collection<? extends GrantedAuthority> getAuthorities(
            RoleEntity role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }
}

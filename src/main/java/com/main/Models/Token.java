package com.main.Models;

import com.main.Entities.TokenEntity;

import java.util.UUID;

public class Token {

    private UUID id;

    private String accessToken;

    private String refreshToken;

    public Token(TokenEntity token) {
        this.id = token.getId();
        this.accessToken=token.getAccessToken();
        this.refreshToken=token.getRefreshToken();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

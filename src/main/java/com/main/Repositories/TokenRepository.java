package com.main.Repositories;

import com.main.Entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity getByRefreshTokenAndDeleteAtIsNull(String refreshToken);
    TokenEntity getByIdAndDeleteAtIsNull(UUID id);
}

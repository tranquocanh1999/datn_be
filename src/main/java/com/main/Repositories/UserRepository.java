package com.main.Repositories;

import com.main.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameAndPasswordAndDeleteAtIsNull(String username, String password);
    UserEntity findByUsernameAndDeleteAtIsNull(String username);
    UserEntity findByUsername(String username);
}

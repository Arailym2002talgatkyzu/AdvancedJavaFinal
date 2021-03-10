package com.example.demo.repo;

import com.example.demo.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

    @Override
    void delete(UserEntity user);
}

package io.javabrains.springsecurityjpa.repository;

import io.javabrains.springsecurityjpa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserName(String userName);
}

package com.example.security.dao;

import com.example.security.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author JonSnow
 * @description TODO
 * ate 2020/11/18
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where u.userName = ?1")
    User findByUsername(String userName);
}

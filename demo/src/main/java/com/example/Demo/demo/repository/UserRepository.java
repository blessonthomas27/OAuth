package com.example.Demo.demo.repository;

import com.example.Demo.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
     User findByemailId(String emailId);
}

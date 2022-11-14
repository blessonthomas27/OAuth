package com.example.Demo.demo.repository;

import com.example.Demo.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}

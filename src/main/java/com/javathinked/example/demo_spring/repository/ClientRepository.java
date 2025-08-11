package com.javathinked.example.demo_spring.repository;

import com.javathinked.example.demo_spring.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

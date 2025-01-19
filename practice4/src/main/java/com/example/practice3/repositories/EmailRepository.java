package com.example.practice3.repositories;

import com.example.practice3.entities.Email;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailRepository extends CrudRepository<Email, UUID> {
}

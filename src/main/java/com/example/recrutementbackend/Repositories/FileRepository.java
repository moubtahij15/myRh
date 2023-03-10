package com.example.recrutementbackend.Repositories;

import com.example.recrutementbackend.Entities.Agent;
import com.example.recrutementbackend.Entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
}

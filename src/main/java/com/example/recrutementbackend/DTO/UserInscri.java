package com.example.recrutementbackend.DTO;

import com.example.recrutementbackend.Entities.Recruteur;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserInscri {
    private String recruteur;
    private MultipartFile file;
}

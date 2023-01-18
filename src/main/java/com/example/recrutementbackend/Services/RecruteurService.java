package com.example.recrutementbackend.Services;

import com.example.recrutementbackend.Entities.Recruteur;
import com.example.recrutementbackend.Repositories.RecruteurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RecruteurService {

    final RecruteurRepository recruteurRepository;
    final PasswordEncoder passwordEncoder;
    final FileService fileService;

    public RecruteurService(RecruteurRepository recruteurRepository, PasswordEncoder passwordEncoder, FileService fileService) {
        this.recruteurRepository = recruteurRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileService = fileService;
    }

    public Recruteur loadUserByUsername(String email) {
        return recruteurRepository.findByEmail(email);
    }

    public Recruteur save(Recruteur recruteur, MultipartFile file) throws Exception {
        recruteur.setPassword(passwordEncoder.encode(recruteur.getPassword()));
        recruteur.setImg_url(fileService.saveAttachment(file));
        return recruteurRepository.save(recruteur);
    }

    public void validerRecruteur(String email) {
        recruteurRepository.validerCompte(email);
    }

    public Recruteur loadUserById(long id) {
        return recruteurRepository.findRecruteurById(id);
    }
}

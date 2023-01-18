package com.example.recrutementbackend.Controllers;

import com.example.recrutementbackend.Entities.Profile;
import com.example.recrutementbackend.Services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class ProfilController {

    final ProfileService profileService;


    public ProfilController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("profiles")
    ResponseEntity<Map<String, List<Profile>>> findAllValider() {
        return new ResponseEntity<>(Map.of("profiles", profileService.findAll()), HttpStatus.OK);

    }
}


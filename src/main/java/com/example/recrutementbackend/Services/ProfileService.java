package com.example.recrutementbackend.Services;

import com.example.recrutementbackend.Entities.Profile;
import com.example.recrutementbackend.Repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


     public List<Profile> findAll() {
        return profileRepository.findAll();
    }
}

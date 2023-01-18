package com.example.recrutementbackend.Repositories;

import com.example.recrutementbackend.Entities.Profile;
import com.example.recrutementbackend.Entities.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
     Profile findProfileById(Long id);


}

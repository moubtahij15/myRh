package com.example.recrutementbackend.Repositories;

import com.example.recrutementbackend.Entities.Agent;
import com.example.recrutementbackend.Entities.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Long> {
    public Recruteur findByEmail(String email);
    public Recruteur findRecruteurById(Long id);


    @Modifying
    @Transactional
    @Query("update Recruteur r set r.etat='VALIDER' where r.email=:email")
    public void validerCompte(@Param("email") String email);

}

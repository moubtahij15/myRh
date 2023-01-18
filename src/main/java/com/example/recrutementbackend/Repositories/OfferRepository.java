package com.example.recrutementbackend.Repositories;

import com.example.recrutementbackend.Entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offre, Long> {

    @Modifying
    @Transactional
    @Query("update Offre o set o.etat='VALIDER' where o.id=:id")
    public void validerOffre(@Param("id") Long id);

//    @Query("select o from  Offre o where  o.niveau_etude=:niveau_etude  and o.ville=:ville and o.profile_id=:profile_id")
//    List<Offre> findAllByNiveau_etudeAndVilleAndProfile_id(@Param("niveau_etude") String niveau_etude, @Param("ville") String ville, @Param("profile_id") long profile_id);

}

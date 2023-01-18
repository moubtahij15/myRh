package com.example.recrutementbackend.Controllers;


import com.example.recrutementbackend.DTO.SearchOffreRequest;
import com.example.recrutementbackend.Entities.Offre;
import com.example.recrutementbackend.Helpers.Enum;
import com.example.recrutementbackend.Services.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OfferController {

    OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }


    @PreAuthorize("hasAuthority('SCOPE_RECRUTEUR')")
    @PostMapping("offer")
    ResponseEntity<Map<String, String>> save(@RequestBody Offre offre) {
        if (offerService.save(offre) != null) {
            return new ResponseEntity<>(Map.of("statut", "Bien Ajouter,En attente la confirmation du offre"), HttpStatus.OK);
        }
        return null;
    }

    @PreAuthorize("hasAuthority('SCOPE_AGENT')")
    @GetMapping("offer/valider/{id}")
    ResponseEntity<Map<String, String>> valider(@PathVariable Long id) {
        offerService.validerOffre(id);
        return new ResponseEntity<>(Map.of("statut", "Bien Valider"), HttpStatus.OK);

    }

    @GetMapping("offer")
    ResponseEntity<Map<String, List<Offre>>> findAll() {
        return new ResponseEntity<>(Map.of("statut", offerService.findAll()), HttpStatus.OK);

    }

    @GetMapping("offers")
    List<Offre> findAlls() {
        return offerService.findAll();
    }

    @PreAuthorize("hasAuthority('SCOPE_AGENT')")
    @GetMapping("offer/enattente")
    ResponseEntity<Map<String, List<Offre>>> findAllEnAttente() {
        return new ResponseEntity<>(Map.of("offres", offerService.findAllByEtat(Enum.Etat.EN_ATTENTE.toString())), HttpStatus.OK);

    }

    @GetMapping("offer/valider")
    ResponseEntity<Map<String, List<Offre>>> findAllValider() {
        return new ResponseEntity<>(Map.of("offres", offerService.findAllByEtat(Enum.Etat.VALIDER.toString())), HttpStatus.OK);

    }

    @PostMapping("searchOffre")
    ResponseEntity<Map<String, List<Offre>>> search(@RequestBody SearchOffreRequest searchOffreRequest) {
        return new ResponseEntity<>(Map.of("offres", offerService.searchOffre(searchOffreRequest)), HttpStatus.OK);

    }


}

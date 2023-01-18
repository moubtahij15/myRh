package com.example.recrutementbackend.Services;

import com.example.recrutementbackend.DTO.SearchOffreRequest;
import com.example.recrutementbackend.Entities.Offre;
import com.example.recrutementbackend.Repositories.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OfferService {
    final OfferRepository offerRepository;
//    final P

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offre save(Offre offre) {
        offerRepository.save(offre);
        return offre;
    }

    public void validerOffre(Long id) {
        offerRepository.validerOffre(id);
    }


    public List<Offre> findAllByEtat(String etat) {
        return offerRepository.findAll().stream()
                .filter(offre -> offre.getEtat().equals(etat))
                .sorted(Comparator.comparingInt(value -> -Math.toIntExact(value.getId()))).toList();
    }

    public List<Offre> findAll() {
        return offerRepository.findAll();
    }


    public List<Offre> searchOffre(SearchOffreRequest searchOffreRequest) {


        List<Offre> offres = offerRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(value -> -Math.toIntExact(value.getId()))).toList();

        if (!searchOffreRequest.getNiveau_etude().equals("")) {
            offres = offres.stream().filter(offre -> offre.getNiveau_etude().equals(searchOffreRequest.getNiveau_etude())
            ).toList();
        }
        if (!searchOffreRequest.getVille().equals("")) {
            offres = offres.stream().filter(offre ->
                    offre.getVille().equals(searchOffreRequest.getVille())).toList();
        }
        if (searchOffreRequest.getProfile_id() != 0) {
            offres = offres.stream().filter(offre ->
                    offre.getProfile_id() == searchOffreRequest.getProfile_id()
            ).toList();
        }
        return offres;

    }
}

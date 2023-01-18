package com.example.recrutementbackend.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Recruteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;

    private String password;
    private String nom;
    @ManyToOne
    @JoinColumn(name = "img_url_id")
    private File img_url;
    private String adresse;
    private String tel;
    private String entreprise_name;
    private String etat = "NON_VALIDER";
    private String role = "RECRUTEUR";


    @JsonIgnore
    @OneToMany(mappedBy = "recruteur")
    private Collection<Offre> offresByRecruteur = new ArrayList<>();

    public Recruteur(String email, String password, String nom, String adresse, String tel, String role,String entreprise_name) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.role = role;
        this.entreprise_name=entreprise_name;
    }

    public Recruteur() {

    }
}

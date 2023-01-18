package com.example.recrutementbackend.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

        private String description;
    @Basic

    private String etat="EN_ATTENTE";
    @Basic

    private String ville;
    private String titre;
    @Basic
    private String niveau_etude;
    @Basic

    private String salaire;
    @Basic
    @Column(name = "profile_id", nullable = true)

    Long profile_id;
    @Basic
    @Column(name = "recruteur_id", nullable = true)

    Long recruteur_id;
    @Basic
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "recruteur_id", referencedColumnName = "id")
    private Recruteur recruteur;

    @Override
    public String toString() {
        return "Offre{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", etat='" + etat + '\'' +
                ", ville='" + ville + '\'' +
                ", niveau_etude='" + niveau_etude + '\'' +
                ", salaire='" + salaire + '\'' +
                ", profile_id=" + profile_id +
                ", recruteur_id=" + recruteur_id +
                '}';
    }
}

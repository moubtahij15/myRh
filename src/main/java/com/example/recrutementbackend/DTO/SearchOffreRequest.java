package com.example.recrutementbackend.DTO;

import lombok.Data;

@Data
public class SearchOffreRequest {
    private String ville, niveau_etude;
    private long profile_id = 0;

}

package com.example.recrutementbackend.Helpers;

import com.example.recrutementbackend.Entities.Agent;
import com.example.recrutementbackend.Entities.Recruteur;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Logged {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Agent agent;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Recruteur recruteur;
    String accessToken;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String refreshToken;
    String role;
}

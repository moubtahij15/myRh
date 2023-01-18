package com.example.recrutementbackend.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    private String password;
    private String role="AGENT";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

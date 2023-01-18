package com.example.recrutementbackend.Helpers;

// Importing required classes

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

// Annotations
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
// Class
public class EmailDetails {

    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
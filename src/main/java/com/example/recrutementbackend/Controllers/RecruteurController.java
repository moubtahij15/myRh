package com.example.recrutementbackend.Controllers;


import com.example.recrutementbackend.Entities.File;
import com.example.recrutementbackend.Entities.Recruteur;
import com.example.recrutementbackend.Helpers.EmailDetails;
import com.example.recrutementbackend.Helpers.JwtUtil;
import com.example.recrutementbackend.Helpers.TokenGenerator;
import com.example.recrutementbackend.Services.EmailServiceImpl;
import com.example.recrutementbackend.Services.FileService;
import com.example.recrutementbackend.Services.RecruteurService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RecruteurController {


    final RecruteurService recruteurService;
    final EmailServiceImpl emailService;
    final EmailDetails emailDetails;
    final TokenGenerator tokenGenerator;
    final JwtDecoder jwtDecoder;
    final FileService fileService;


    public RecruteurController(RecruteurService recruteurService, EmailServiceImpl emailService, EmailDetails emailDetails, TokenGenerator tokenGenerator, JwtDecoder jwtDecoder, FileService fileService) {
        this.recruteurService = recruteurService;
        this.emailService = emailService;
        this.emailDetails = emailDetails;
        this.tokenGenerator = tokenGenerator;
        this.jwtDecoder = jwtDecoder;
        this.fileService = fileService;
    }

    @PostMapping(value = "inscription")
    public ResponseEntity<Map<String, String>> inscription(@RequestPart(required = false) MultipartFile file, @RequestPart Recruteur recruteur) throws Exception {
        String status = null;
        String token = tokenGenerator.execute(recruteur.getEmail(), false, "Recruteure", JwtUtil.EXPIRE_VERIFY_TOKEN, 0).getAccessToken();
        if (recruteurService.save(recruteur, file) != null) {

            emailDetails.setRecipient(recruteur.getEmail());
            emailDetails.setSubject("Email Verification");
            emailDetails.setMsgBody("Dear User,\nThank you for signing up for our service. In order to complete your registration, please click on the following link to verify your email address:\n http://localhost:8080/verify/" + token
                    + "\n\nIf you did not sign up for our service, you can ignore this email.\n\nSincerely,\n[Your Name]"


            );
            status = emailService.sendSimpleMail(emailDetails);
        }
//

        Map<String, String> idToken = new HashMap<>();
        idToken.put("status", status);

        return new ResponseEntity<>(idToken, HttpStatus.OK);
//        return null;
    }

    @GetMapping(value = "resend/{id}")
    public ResponseEntity<Map<String, String>> resend(@PathVariable long id) throws Exception {
        Recruteur recruteur = recruteurService.loadUserById(id);
        String status = null;
        String token = tokenGenerator.execute(recruteur.getEmail(), false, "Recruteure", JwtUtil.EXPIRE_VERIFY_TOKEN, 0).getAccessToken();
        emailDetails.setRecipient(recruteur.getEmail());
        emailDetails.setSubject("Email Verification");
        emailDetails.setMsgBody("Dear User,\nThank you for signing up for our service. In order to complete your registration, please click on the following link to verify your email address:\n http://localhost:8080/verify/" + token
                + "\n\nIf you did not sign up for our service, you can ignore this email.\n\nSincerely,\n[Your Name]");
        status = emailService.sendSimpleMail(emailDetails);
        Map<String, String> idToken = new HashMap<>();
        idToken.put("status", status);

        return new ResponseEntity<>(idToken, HttpStatus.OK);
    }

    @GetMapping("verify/{token}")
    public ResponseEntity<Map<String, String>> verifier(@PathVariable String token) {
        try {
            Jwt decodeJWT = jwtDecoder.decode(token);
            String subject = decodeJWT.getSubject();
            recruteurService.validerRecruteur(subject);
            System.out.println(subject);
            return new ResponseEntity<>(Map.of("Success", "votre compte était bien validé"), HttpStatus.OK);

        } catch (JwtException e) {
            return new ResponseEntity<>(Map.of("errorMessage", e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        System.out.println("from dddd");
//        return nu
        File attachment = null;
        attachment = fileService.getAttachment(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}

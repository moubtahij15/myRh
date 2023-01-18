package com.example.recrutementbackend.Controllers;

import com.example.recrutementbackend.DTO.TokenRequest;
import com.example.recrutementbackend.Entities.Agent;
import com.example.recrutementbackend.Entities.Recruteur;
import com.example.recrutementbackend.Helpers.EmailDetails;
import com.example.recrutementbackend.Helpers.Enum;
import com.example.recrutementbackend.Helpers.JwtUtil;
import com.example.recrutementbackend.Helpers.Logged;
import com.example.recrutementbackend.Helpers.TokenGenerator;
import com.example.recrutementbackend.Repositories.AgentRepository;
import com.example.recrutementbackend.Repositories.RecruteurRepository;
import com.example.recrutementbackend.Services.EmailServiceImpl;
import com.example.recrutementbackend.Services.UserDetailServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import net.minidev.json.JSONValue;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    private TokenGenerator tokenGenerator;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    final EmailServiceImpl emailService;
    private final RecruteurRepository recruteurRepository;
    private final AgentRepository agentRepository;

    public AuthController(TokenGenerator tokenGenerator, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, EmailServiceImpl emailService,
                          RecruteurRepository recruteurRepository,
                          AgentRepository agentRepository) {
        this.tokenGenerator = tokenGenerator;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.emailService = emailService;
        this.recruteurRepository = recruteurRepository;
        this.agentRepository = agentRepository;
    }

    @PostMapping("/token")
    public ResponseEntity<Object> jwtToken(@RequestBody TokenRequest tokenRequest) throws JsonProcessingException {
        String subject = null;
        String scope = null;
        Authentication authentication = null;
        System.out.printf(String.valueOf(tokenRequest));
        UserDetailServiceImp.role = tokenRequest.getRole();

        Logged user = new Logged();
        Logged logged = new Logged();

        if (tokenRequest.getGrantType().equals("password")) {
            System.out.println("pppppmpm");

            try {
                authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword()));

            } catch (Exception e) {
                return new ResponseEntity<>(Map.of("statut", "error",
                        "type", "bad credentials"
                        , "errorMessage", e.getMessage())
                        , HttpStatus.OK);

            }


            if (tokenRequest.getRole().equals("RECRUTEURE")) {
                user.setRecruteur(recruteurRepository.findByEmail(authentication.getName()));
                if (user.getRecruteur().getEtat().equals("NON_VALIDER")) {
                    return new ResponseEntity<>(Map.of("statut", "error",
                            "type", "validation",
                            "id",user.getRecruteur().getId()
                            , "errorMessage", "le compte pas encore valide  checker votre boite mail"), HttpStatus.OK);
                }
            } else {
                user.setAgent(agentRepository.findByEmail(authentication.getName()));

            }

            subject = authentication.getName();
            scope = authentication.getAuthorities()
                    .stream().map(aut -> aut.getAuthority())
                    .collect(Collectors.joining(" "));


        } else if (tokenRequest.getGrantType().equals("refreshToken")) {
            if (tokenRequest.getRefreshToken() == null) {
                return new ResponseEntity<>(Map.of("errorMessage", "Refresh  Token is required"), HttpStatus.UNAUTHORIZED);
            }
            Jwt decodeJWT = null;
            try {
                decodeJWT = jwtDecoder.decode(tokenRequest.getRefreshToken());
            } catch (JwtException e) {
                return new ResponseEntity<>(Map.of("errorMessage", e.getMessage()), HttpStatus.UNAUTHORIZED);
            }
            subject = decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope = authorities.stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(" "));
        }

        logged = tokenGenerator.execute(subject, tokenRequest.isWithRefreshToken(), scope, JwtUtil.EXPIRE_ACCESS_TOKEN, JwtUtil.EXPIRE_REFRESH_TOKEN);
        logged.setAgent(user.getAgent());
        logged.setRecruteur(user.getRecruteur());
        return ResponseEntity.ok(logged);


    }

    @GetMapping("send")
    public String
    sendMail(@RequestBody EmailDetails details) {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }


    @GetMapping(path = "/profilae")
    public ResponseEntity<String> myEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // check the user's roles and perform the appropriate logic
        System.out.println(authorities);
        return new ResponseEntity<>("Endpoint accessed", HttpStatus.OK);
    }

}

package com.example.recrutementbackend.Services;


import com.example.recrutementbackend.Entities.Agent;
import com.example.recrutementbackend.Entities.Recruteur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    AgentService agentService;
    RecruteurService recruteurService;
    public static String error = "";

    public UserDetailServiceImp(AgentService agentService, RecruteurService recruteurService) {
        this.agentService = agentService;
        this.recruteurService = recruteurService;
    }

    public static String role = "AGENT";


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("from loadUserByUsername");
        System.out.println("-------");
        System.out.println(username);
        System.out.println("-------");
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (role.equals("AGENT")) {
            Agent agent = (Agent) agentService.loadUserByUsername(username);
            grantedAuthorities.add(new SimpleGrantedAuthority(agent.getRole()));
            return new User(agent.getEmail(), agent.getPassword(), grantedAuthorities);

        } else if (role.equals("RECRUTEURE")) {
            Recruteur recruteur = (Recruteur) recruteurService.loadUserByUsername(username);
            grantedAuthorities.add(new SimpleGrantedAuthority(recruteur.getRole()));

            return new User(recruteur.getEmail(), recruteur.getPassword(), grantedAuthorities);

        }
        return null;

    }


}

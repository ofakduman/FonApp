package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.model.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private PersonnelService personnelService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Personnel personnel = personnelService.findByPersonnelUsername(username);

        logger.info("Personel arama sonucu: {}", personnel);

        logger.error(username);


        if (personnel == null) {
            logger.warn("Kullanıcı bulunamadı: {}", username);
            throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equalsIgnoreCase(personnel.getPersonnelDuty())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
        } else if ("personnel".equalsIgnoreCase(personnel.getPersonnelDuty())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_personnel"));
        }
        logger.debug("-------------------------------Kullanıcı yetkileri: {}", authorities);

        return new User(personnel.getPersonnelUsername(), personnel.getHashedPassword(), authorities);
    }
}

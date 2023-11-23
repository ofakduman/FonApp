package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.model.Personnel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomUserDetailsServiceTest {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private PersonnelServiceImpl personnelService;

    @Test
    public void testLoadUserByUsername() {
        Personnel mockPersonnel = new Personnel();
        mockPersonnel.setPersonnelUsername("admin");
        mockPersonnel.setPersonnelDuty("admin");
        mockPersonnel.setHashedPassword("admin");

        when(personnelService.findByPersonnelUsername("admin")).thenReturn(mockPersonnel);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("admin");

        assertNotNull(userDetails);
    }

    @Test
    public void testUserNotFound() {
        when(personnelService.findByPersonnelUsername("unknownUser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername("unknownUser");
        });
    }
}

package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.PersonnelDto;
import com.digitalcreators.digicreatefon.model.Personnel;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.Valid;
import java.util.List;

public interface PersonnelService {

    public Personnel savePersonnel(Personnel personnel);

    public List<Personnel> getAllPersonnels();

    public Personnel updatePersonnel(Long id, Personnel personnel);

    public void deleteById(Long id);

    public boolean registerPersonnel(@Valid PersonnelDto personnelDto);

    public boolean authenticatePersonnel(String username, String plainPassword) ;

    public String hashPassword(String plainPassword);

    public boolean checkPassword(String plainPassword, String hashedPassword) ;

    public Personnel findByPersonnelUsername(String username) ;

    public boolean isUsernameAvailable(String username) ;

    public List<Personnel> getPersonalsByPersonnelUsername(String username);

}

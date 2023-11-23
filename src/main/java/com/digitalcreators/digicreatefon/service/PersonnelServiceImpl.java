package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.PersonnelDto;
import com.digitalcreators.digicreatefon.model.Personnel;
import com.digitalcreators.digicreatefon.repository.PersonnelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor

public class PersonnelServiceImpl implements PersonnelService{

    private final PersonnelRepository personnelRepository;

    public Personnel savePersonnel(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    public List<Personnel> getAllPersonnels() {
        return personnelRepository.findAll();
    }

    public Personnel updatePersonnel(Long id, Personnel personnel){
        Personnel existingPersonnel= personnelRepository.findById(id).get();

        existingPersonnel.setPersonnelUsername(personnel.getPersonnelUsername());
        existingPersonnel.setHashedPassword(personnel.getHashedPassword());
        existingPersonnel.setName(personnel.getName());
        existingPersonnel.setSurname(personnel.getSurname());
        existingPersonnel.setTcNo(personnel.getTcNo());
        existingPersonnel.setMail(personnel.getMail());
        existingPersonnel.setPersonnelDuty(personnel.getPersonnelDuty());


        return personnelRepository.save(existingPersonnel);
    }

    public void deleteById(Long id)
    {
        personnelRepository.deleteById(id);
    }


    public boolean registerPersonnel(@Valid PersonnelDto personnelDto) {
        // Kullanıcı adının daha önce alınıp alınmadığını kontrol et
        Personnel existingPersonnel = personnelRepository.findByPersonnelUsername(personnelDto.getPersonnelUsername());
        if (existingPersonnel != null) {
            throw new RuntimeException("Bu kullanıcı adı zaten alınmış!");
        }

        Personnel personnel = new Personnel(personnelDto);
        String hashedPassword = hashPassword(personnelDto.getHashedPassword());
        personnel.setHashedPassword(hashedPassword);

        Personnel savedPersonnel = personnelRepository.save(personnel);

        return savedPersonnel != null;
    }

    public boolean authenticatePersonnel(String username, String plainPassword) {
        Personnel personnel = personnelRepository.findByPersonnelUsername(username);
        if (personnel == null) return false;
        return checkPassword(plainPassword, personnel.getHashedPassword());
    }

    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public Personnel findByPersonnelUsername(String username) {
        return personnelRepository.findByPersonnelUsername(username);

    }

    public boolean isUsernameAvailable(String username) {
        Personnel existingPersonnel = personnelRepository.findByPersonnelUsername(username);
        return existingPersonnel==null;
    }

    public List<Personnel> getPersonalsByPersonnelUsername(String username) {
        return personnelRepository.findByPersonnelUsernameContaining(username);
    }

}




package com.digitalcreators.digicreatefon.repository;


import com.digitalcreators.digicreatefon.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
//    Personnel findBypersonal(String personnel_username);
    Personnel findByPersonnelUsername(String personnelUsername);
    List<Personnel> findByPersonnelUsernameContaining(String username);


}

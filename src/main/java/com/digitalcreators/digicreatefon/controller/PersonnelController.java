package com.digitalcreators.digicreatefon.controller;

import java.util.List;

import com.digitalcreators.digicreatefon.dto.PersonnelDto;
import com.digitalcreators.digicreatefon.model.Personnel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digitalcreators.digicreatefon.service.PersonnelServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Personnels")
@RequiredArgsConstructor
public class PersonnelController {

    private final PersonnelServiceImpl personnelService;

    @GetMapping
    public List<Personnel> getAllPersonnels() {
        return personnelService.getAllPersonnels();
    }


    @PostMapping
    public ResponseEntity<Boolean> createPersonnel(@RequestBody PersonnelDto personnelDto) {
        boolean isSuccess = personnelService.registerPersonnel(personnelDto);
        return ResponseEntity.ok(isSuccess);
    }

    @GetMapping("/byUsername")
    public ResponseEntity<List<Personnel>> getPersonalsByUsername(@RequestParam(value = "username", required = false) String username) {
        if (username != null && !username.trim().isEmpty()) {
            List<Personnel> personals = personnelService.getPersonalsByPersonnelUsername(username);
            if (personals != null && !personals.isEmpty()) {
                return ResponseEntity.ok(personals);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }


    @GetMapping("/isUsernameAvailable")
    public ResponseEntity<Boolean> checkUsernameAvailability(@RequestParam(value = "username") String username) {
        boolean isAvailable = personnelService.isUsernameAvailable(username);

        if (isAvailable) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }




    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticatePersonnel(@RequestBody PersonnelDto personnelDto) {
        boolean isAuthenticated = personnelService.authenticatePersonnel(personnelDto.getPersonnelUsername(), personnelDto.getHashedPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Doğrulama başarılı.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kullanıcı adı veya şifre yanlış.");
        }
    }

//    @PatchMapping("/{id}")
//    public Personnel updatePersonnel(@PathVariable Long id, @RequestBody PersonnelDto personnelDto){
//        return personnelService.updatePersonnel(id, personnelDto);
//
//    }

    @GetMapping("/hashPassword")
    public ResponseEntity<String> hashPassword(@RequestParam String plainPassword) {
        String hashedPassword = personnelService.hashPassword(plainPassword);
        return ResponseEntity.ok(hashedPassword);
    }

    @PutMapping("/updatePersonnel/{id}")
    public Personnel updateUsername(
            @PathVariable Long id,
            @RequestBody Personnel personnel){

        return personnelService.updatePersonnel(id, personnel);
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        personnelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
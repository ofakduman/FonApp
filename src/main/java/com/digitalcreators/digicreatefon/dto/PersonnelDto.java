package com.digitalcreators.digicreatefon.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PersonnelDto {

    private String personnelUsername;
    private String hashedPassword;
    private String name;
    private String surname;
    private String tcNo;
    private String mail;
    private String personnelDuty;

}

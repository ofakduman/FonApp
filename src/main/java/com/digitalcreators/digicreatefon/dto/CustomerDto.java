package com.digitalcreators.digicreatefon.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CustomerDto {

    private String TCNO;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private String placeOfBirth;
    private String city;
    private String address;

    @Override
    public String toString() {
        return "CustomerDto {" +
                "TCNO='" + TCNO + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}

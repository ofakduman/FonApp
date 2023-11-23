package com.digitalcreators.digicreatefon.model;



import com.digitalcreators.digicreatefon.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "Customer")
public class Customer {

    public Customer() {
    }

    public Customer(CustomerDto customerDto) {
        this.TCNO=customerDto.getTCNO();
        this.name=customerDto.getName();
        this.surname=customerDto.getSurname();
        this.phoneNumber= customerDto.getPhoneNumber();
        this.email= customerDto.getEmail();
        this.birthDate=customerDto.getBirthDate();
        this.placeOfBirth=customerDto.getPlaceOfBirth();
        this.address=customerDto.getAddress();
        this.city=customerDto.getCity();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerID;

    @Column(name = "tcno", nullable = false)
    private String TCNO;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;

    @Column(name = "phonenumber", nullable = false)
    private String phoneNumber;

    @Column(name = "mail", nullable = false)
    private String email;

    @Column(name = "birthdate", nullable = false)
    private Date birthDate;

    @Column(name = "placeofbirth", nullable = false)
    private String placeOfBirth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;


}

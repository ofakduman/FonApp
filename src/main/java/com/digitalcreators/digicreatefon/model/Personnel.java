package com.digitalcreators.digicreatefon.model;



import com.digitalcreators.digicreatefon.dto.PersonnelDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "Personnel")
public class Personnel {

    public Personnel() {
    }

    public Personnel(PersonnelDto personnelDto) {
        this.personnelUsername=personnelDto.getPersonnelUsername();
        this.hashedPassword=personnelDto.getHashedPassword();
        this.name=personnelDto.getName();
        this.surname=personnelDto.getSurname();
        this.tcNo=personnelDto.getTcNo();
        this.mail=personnelDto.getMail();
        this.personnelDuty=personnelDto.getPersonnelDuty();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personnelid;

    @Column(name = "personnelusername", nullable = false)
    private String personnelUsername;

    @Column(name = "personnelpassword", nullable = false)
    private String hashedPassword;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "tcno", nullable = false)
    private String tcNo;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "personnelduty", nullable = false)
    private String personnelDuty;

}

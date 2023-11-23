package com.digitalcreators.digicreatefon.model;

import com.digitalcreators.digicreatefon.dto.SystemDateDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "systemdate")
public class SystemDate {

    public SystemDate() {
    }

    SystemDate(SystemDateDto systemDateDto){
        system_date = systemDateDto.getSystem_date();
        syste_date_time = systemDateDto.getSystem_date_time();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "system_date", nullable = false)
    private java.sql.Date system_date;

    @Column(name = "system_date_time", nullable = true)
    private java.util.Date syste_date_time;

    public void setSystem_date(Date system_date) {
        this.system_date = system_date;
    }
}

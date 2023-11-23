package com.digitalcreators.digicreatefon.model;

import com.digitalcreators.digicreatefon.dto.FundArchiveDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "fundarchive")
public class FundArchive {
    public FundArchive() {
    }

    public FundArchive(FundArchiveDto fundArchiveDto){
        this.fundCode=fundArchiveDto.getFundCode();
        this.date=fundArchiveDto.getDate();
        this.price=fundArchiveDto.getPrice();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long archiveid;

    @Column(name = "fundcode", nullable = false)
    private String fundCode;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "price", nullable = false)
    private float price;

}

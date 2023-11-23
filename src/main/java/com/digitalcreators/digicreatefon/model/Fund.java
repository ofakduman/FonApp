package com.digitalcreators.digicreatefon.model;

import com.digitalcreators.digicreatefon.dto.FundDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "fund")
public class Fund implements Serializable {

    public Fund(){

    }

    public Fund(FundDto fundDto) {
        this.fundCode=fundDto.getFundCode();
        this.fundName=fundDto.getFundName();
        this.fundType=fundDto.getFundType();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fundid;

    @Column(name = "fundcode", nullable = false)
    private String fundCode;

    @Column(name = "fundname", nullable = false)
    private String fundName;

    @Column(name = "fundtype")
    private String fundType;


}

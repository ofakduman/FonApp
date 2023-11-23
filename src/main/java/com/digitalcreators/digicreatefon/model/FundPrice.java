package com.digitalcreators.digicreatefon.model;

import com.digitalcreators.digicreatefon.dto.FundPriceDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "fundprice")
public class FundPrice implements Serializable {
    public FundPrice() {
    }

    public FundPrice(FundPriceDto fundPriceDto){
        this.fundCode=fundPriceDto.getFundCode();
        this.currentPrice=fundPriceDto.getCurrentPrice();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "fundcode", nullable = false)
    private String fundCode;

    @Column(name = "currentprice", nullable = false)
    private float currentPrice;

}

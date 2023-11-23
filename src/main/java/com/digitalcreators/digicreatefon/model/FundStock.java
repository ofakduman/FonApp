package com.digitalcreators.digicreatefon.model;

import com.digitalcreators.digicreatefon.dto.FundStockDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "fundstock")
public class FundStock {
    public FundStock() {
    }

    public FundStock(FundStockDto fundStockDto){
        this.fundCode=fundStockDto.getFundCode();
        this.customerId=fundStockDto.getCustomerId();
        this.amount=fundStockDto.getAmount();
        this.totalPrice=fundStockDto.getTotalPrice();

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockid", nullable = false)
    private Long id;

    @Column(name = "fundcode", nullable = false)
    private String fundCode;

    @Column(name ="customerid", nullable = false)
    private Long customerId;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "totalprice", nullable = false)
    private float totalPrice;
}

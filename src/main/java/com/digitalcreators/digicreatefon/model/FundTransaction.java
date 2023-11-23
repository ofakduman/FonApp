package com.digitalcreators.digicreatefon.model;

import com.digitalcreators.digicreatefon.dto.FundTransactionDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "fundtransaction")
public class FundTransaction {
    public FundTransaction() {
    }

    public FundTransaction(FundTransactionArchive fund_trans_archive) {
        this.setTransactionPrice(fund_trans_archive.getPrice());
        this.setRoute(fund_trans_archive.getRoute());
        this.setFundcode(fund_trans_archive.getFundcode());
        this.setAmount(fund_trans_archive.getAmount());
        this.setCustomerId(fund_trans_archive.getCustomerId());
    }


    public FundTransaction(FundTransactionDto fundTransactionDto){
        this.fundcode=fundTransactionDto.getFundcode();
        this.customerId=fundTransactionDto.getCustomerId();
        this.route=fundTransactionDto.getRoute();
        this.amount=fundTransactionDto.getAmount();
//        this.transactionPrice=fundTransactionDto.getTransactionPrice();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionid", nullable = false)
    private Long transactionId;

    @Column(name = "fundcode", nullable = false)
    private String fundcode;

    @Column(name = "customerid", nullable = false)
    private Long customerId;

    @Column(name = "route", nullable = false)
    private int route;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "transactionprice", nullable = false)
    private float transactionPrice;

}


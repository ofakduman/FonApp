package com.digitalcreators.digicreatefon.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "fundtransactionarchive")
public class FundTransactionArchive {
    public FundTransactionArchive() {
    }

    public FundTransactionArchive(FundTransaction fundTransaction) {
        this.transactionId = fundTransaction.getTransactionId();
        this.customerId = fundTransaction.getCustomerId();
        this.fundcode = fundTransaction.getFundcode();
        this.route = fundTransaction.getRoute();
        this.amount = fundTransaction.getAmount();
        this.price = fundTransaction.getTransactionPrice();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transactionid", nullable = false)
    private Long transactionId;

    @Column(name = "customerid", nullable = false)
    private Long customerId;

    @Column(name = "fundcode", nullable = false)
    private String fundcode;

    @Column(name = "route", nullable = false)
    private int route;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerid", nullable = false, insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fundcode", referencedColumnName = "fundcode", insertable = false, updatable = false)
    private Fund fund;

}

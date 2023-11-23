package com.digitalcreators.digicreatefon.model;


import com.digitalcreators.digicreatefon.dto.CashTransactionDto;
import com.digitalcreators.digicreatefon.dto.GeneralCashTransactionDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "cashtransaction")
public class CashTransaction {
    public CashTransaction() {
    }

    public CashTransaction(CashTransactionDto cashTransactionDto){
        this.customerId=cashTransactionDto.getCustomerId();
        this.route=cashTransactionDto.getRoute();
        this.amount=cashTransactionDto.getAmount();
    }

    public CashTransaction(CashTransactionArchive cashTransactionArch){
        this.customerId=cashTransactionArch.getCustomerId();
        this.route=cashTransactionArch.getRoute();
        this.amount=cashTransactionArch.getAmount();
        this.canceled = cashTransactionArch.getCanceled();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customerid", nullable = false)
    private Long customerId;

    @Column(name = "route", nullable = false)
    private int route;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "canceled", nullable = true )
    private Boolean canceled = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerid", nullable = false, insertable = false, updatable = false)
    private Customer customer;


    public CashTransaction(GeneralCashTransactionDto generalCashTransactionDto) {
        this.id = generalCashTransactionDto.getCashtransactionid();
        this.customerId = generalCashTransactionDto.getCustomerId();
        this.route = generalCashTransactionDto.getRoute();
        this.amount = generalCashTransactionDto.getAmount();
        this.canceled = generalCashTransactionDto.getCanceled();
        this.customer = generalCashTransactionDto.getCustomer();
    }
}

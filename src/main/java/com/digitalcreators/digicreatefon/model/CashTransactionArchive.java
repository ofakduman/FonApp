package com.digitalcreators.digicreatefon.model;

import com.digitalcreators.digicreatefon.dto.GeneralCashTransactionDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "cashtransactionarchive")
public class CashTransactionArchive {

    public CashTransactionArchive() {
    }

    public CashTransactionArchive(CashTransaction cashTransaction) {

        this.customerId = cashTransaction.getCustomerId();
        this.route = cashTransaction.getRoute();
        this.amount = cashTransaction.getAmount();
        this.cashTransactionId = cashTransaction.getId();
        this.canceled =cashTransaction.getCanceled();
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

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "cashtransactionid", nullable = false)
    private Long cashTransactionId;

    @Column(name = "canceled", nullable = true)
    private Boolean canceled = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerid", nullable = false, insertable = false, updatable = false)
    private Customer customer;

//    public CashTransactionArchive(GeneralCashTransactionDto generalCashTransactionDto) {
//        this.customerId
//    }


    public Boolean getCanceled() {
        return canceled;
    }
}

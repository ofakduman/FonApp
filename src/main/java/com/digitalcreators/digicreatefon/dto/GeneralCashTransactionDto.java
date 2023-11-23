package com.digitalcreators.digicreatefon.dto;

import com.digitalcreators.digicreatefon.model.CashTransaction;
import com.digitalcreators.digicreatefon.model.CashTransactionArchive;
import com.digitalcreators.digicreatefon.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class GeneralCashTransactionDto {
    public GeneralCashTransactionDto(CashTransactionArchive cta) {
        this.cashtransactionid = cta.getCashTransactionId();
        this.customerId = cta.getCustomerId();
        this.route = cta.getRoute();
        this.amount = cta.getAmount();
        this.date = cta.getDate();
        this.canceled = cta.getCanceled();
        this.customer = cta.getCustomer();
        this.isToday = false;
    }


    public GeneralCashTransactionDto(CashTransaction ct) {
        this.cashtransactionid = ct.getId();
        this.customerId = ct.getCustomerId();
        this.route = ct.getRoute();
        this.amount = ct.getAmount();
//        this.date = ct.getDate();
        this.canceled = ct.getCanceled();
        this.customer = ct.getCustomer();
        this.isToday = false;
    }

    public GeneralCashTransactionDto(){

    }

    private Customer customer;
    private String name;
    private String surname;
    private Long cashtransactionid;
    private Long customerId;
    private int route;
    private Long amount;
    private Date date;
    private Boolean canceled = false;
    private Boolean isToday = false;
}

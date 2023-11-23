package com.digitalcreators.digicreatefon.model;

import com.digitalcreators.digicreatefon.dto.CustomerBalanceDto;
import com.digitalcreators.digicreatefon.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "customerbalance")
public class CustomerBalance {

    public CustomerBalance() {
    }

    public CustomerBalance(Customer customer){
        this.customerId=customer.getCustomerID();
        this.balance=0;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long balanceID;

    @Column(name = "customerid", nullable = false)
    private Long customerId;

    @Column(name = "balance", nullable = false)
    private float balance;



}

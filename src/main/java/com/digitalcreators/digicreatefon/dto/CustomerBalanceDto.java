package com.digitalcreators.digicreatefon.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CustomerBalanceDto {


    private Long customerId;
    private float balance;

}

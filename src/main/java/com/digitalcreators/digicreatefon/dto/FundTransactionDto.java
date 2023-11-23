package com.digitalcreators.digicreatefon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundTransactionDto {

    private String fundcode;
    private Long customerId;
    private int route;
    private Long amount;
//    private float transactionPrice;
}

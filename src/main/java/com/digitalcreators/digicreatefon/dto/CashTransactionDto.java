package com.digitalcreators.digicreatefon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashTransactionDto {

    private Long customerId;
    private int route;
    private Long amount;
}

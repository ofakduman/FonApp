package com.digitalcreators.digicreatefon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundStockDto {
    public FundStockDto() {
    }

    public FundStockDto(FundTransactionDto fundTransactionDto){
        this.fundCode=fundTransactionDto.getFundcode();
        this.customerId=fundTransactionDto.getCustomerId();
        this.amount=fundTransactionDto.getAmount();
//        this.totalPrice=fundTransactionDto.getTransactionPrice();
    }


    private String fundCode;
    private Long customerId;
    private Long amount;
    private float totalPrice;
}

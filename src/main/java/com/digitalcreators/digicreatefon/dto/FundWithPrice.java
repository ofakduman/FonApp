package com.digitalcreators.digicreatefon.dto;

import com.digitalcreators.digicreatefon.model.Fund;
import com.digitalcreators.digicreatefon.model.FundPrice;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundWithPrice {

    private Long fundId;
    private String fundCode;
    private String fundName;
    private String fundType;
    private float currentPrice;

    public FundWithPrice(Fund fund, FundPrice fundPrice) {
        this.fundId = fund.getFundid();
        this.fundCode = fund.getFundCode();
        this.fundName = fund.getFundName();
        this.fundType = fund.getFundType();
        this.currentPrice = fundPrice.getCurrentPrice();
    }
}

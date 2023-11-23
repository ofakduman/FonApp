package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.FundPriceDto;
import com.digitalcreators.digicreatefon.model.FundPrice;

import java.util.List;

public interface FundPriceService {

    public FundPrice saveFundPrice(FundPriceDto fundPriceDto);

    public FundPrice getFundPriceByFundCode(String fundCode);

    public List<FundPrice> getAllFundPrices();

    public FundPrice updateFundPrice(Long id, FundPriceDto fundPriceDto);

    public FundPrice updateFundPrice(Long id, float _fundPrice);


    public void deleteById(Long id);

}

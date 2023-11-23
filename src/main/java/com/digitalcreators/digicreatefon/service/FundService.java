package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.FundDto;
import com.digitalcreators.digicreatefon.model.Fund;

import java.util.List;

public interface FundService {

    public Fund saveFund(Fund fund);

    public List<Fund> getAllFunds();

    public Fund getFundByFundCode(String fundCode);
    public List<Fund> getFundByFundCodeContaining(String fundCode);



    public Fund updateFund(Long id, FundDto fundDto);


    public void deleteById(int id);

}

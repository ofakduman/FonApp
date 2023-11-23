package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.model.FundStock;

import java.util.List;

public interface FundStockService {

    public FundStock saveFundStock(FundStock fundStock);

    public List<FundStock> getAllFundStocks();

    public FundStock getFundStockByFundCodeAndCustomerId(String fundCode, Long customerId);

    public List<FundStock> getFundStocksByCustomerId(Long customerId);

    public void  deleteById(Long id);

}

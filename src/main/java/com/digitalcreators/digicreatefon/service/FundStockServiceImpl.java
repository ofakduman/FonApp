package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.model.FundStock;
import com.digitalcreators.digicreatefon.repository.FundStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FundStockServiceImpl implements FundStockService{

    private final FundStockRepository fundStockRepository;

    public FundStock saveFundStock(FundStock fundStock){
        return fundStockRepository.save(fundStock);
    }

    public List<FundStock> getAllFundStocks(){
        return fundStockRepository.findAll();
    }

    public FundStock getFundStockByFundCodeAndCustomerId(String fundCode, Long customerId){
        FundStock fundStock=fundStockRepository.findByFundCodeAndCustomerId(fundCode,customerId);
        return fundStock;
    }

    public List<FundStock> getFundStocksByCustomerId(Long customerId){
        return fundStockRepository.findByCustomerId(customerId);
    }

//    public FundStock updateFundStock(Long id, FundStockDto fundStockDto){
//        FundStock existingFundStock= fundStockRepository.findById(id).get();
//
//        existingFundStock.setFundCode(fundStockDto.getFundCode());
//        existingFundStock.setCustomerId(fundStockDto.getCustomerId());
//        existingFundStock.setAmount(fundStockDto.getAmount());
//        existingFundStock.setTotalPrice(fundStockDto.getTotalPrice());
//
//
//        return fundStockRepository.save(existingFundStock);
//    }

    public void  deleteById(Long id){
        fundStockRepository.deleteById(id);
    }

    public void getFilteredData(Date date, Date date1){

    }


}



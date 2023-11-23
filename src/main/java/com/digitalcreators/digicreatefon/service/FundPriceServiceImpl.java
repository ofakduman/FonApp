package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.FundPriceDto;
import com.digitalcreators.digicreatefon.model.FundPrice;
import com.digitalcreators.digicreatefon.repository.FundPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FundPriceServiceImpl implements FundPriceService {

    private final FundPriceRepository fundPriceRepository;

    public FundPrice saveFundPrice(FundPriceDto fundPriceDto){
        FundPrice fp=fundPriceRepository.findByFundCode(fundPriceDto.getFundCode());
        if(fp==null){
            FundPrice fundPrice=new FundPrice(fundPriceDto);
            return fundPriceRepository.save(fundPrice);
        }
        return updateFundPrice(fp.getId(),fundPriceDto);
    }

    public FundPrice getFundPriceByFundCode(String fundCode){
        FundPrice fundPrice=fundPriceRepository.findByFundCode(fundCode);
        return fundPrice;
    }

    public List<FundPrice> getAllFundPrices(){
        return fundPriceRepository.findAll();
    }

    public FundPrice updateFundPrice(Long id, FundPriceDto fundPriceDto){
        FundPrice existingFundPrice= fundPriceRepository.findById(id).get();
//        existingCustomer.setCustomerID(customer.getCustomerID());
//        existingFundPrice.setFundCode(fundPriceDto.getFundCode());
        existingFundPrice.setCurrentPrice(fundPriceDto.getCurrentPrice());


        return fundPriceRepository.save(existingFundPrice);
    }

    @Override
    public FundPrice updateFundPrice(Long id, float _fundPrice) {
        FundPrice existingFundPrice= fundPriceRepository.findById(id).get();
        existingFundPrice.setCurrentPrice(_fundPrice);
        return fundPriceRepository.save(existingFundPrice);
    }


    public void deleteById(Long id){
        fundPriceRepository.deleteById(id);
    }
}

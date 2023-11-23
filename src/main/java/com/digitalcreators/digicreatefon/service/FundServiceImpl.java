package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.FundDto;
import com.digitalcreators.digicreatefon.model.Fund;
import com.digitalcreators.digicreatefon.repository.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FundServiceImpl implements FundService{

    @Autowired
    private final FundRepository fundRepository;


    public Fund saveFund(Fund fund){
        return fundRepository.save(fund);
    }

    public List<Fund> getAllFunds(){
        return fundRepository.findAll();
    }

    public Fund getFundByFundCode(String fundCode){
        Fund fund=fundRepository.findByFundCode(fundCode);
        return fund;
    }

    public List<Fund> getFundByFundCodeContaining(String fundCode){
        return  fundRepository.findByFundCodeContainingIgnoreCase(fundCode);
    }

    public Fund updateFund(Long id, FundDto fundDto){
        Fund existingFund= fundRepository.findById(id).get();

        existingFund.setFundCode(fundDto.getFundCode());
        existingFund.setFundName(fundDto.getFundName());
        existingFund.setFundType(fundDto.getFundType());


        return fundRepository.save(existingFund);
    }


    public void deleteById(int id){
        fundRepository.deleteById((long) id);
    }
}

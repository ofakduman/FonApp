package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.FundArchiveDto;
import com.digitalcreators.digicreatefon.model.FundArchive;
import com.digitalcreators.digicreatefon.repository.FundArchiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FundArchiveServiceImpl implements FundArchiveService {

    private final FundArchiveRepository fundArchiveRepository;


    public FundArchive saveFundArchive(FundArchive fundArchive){
        return fundArchiveRepository.save(fundArchive);
    }

    public List<FundArchive> getAllFundArchives(){
        return fundArchiveRepository.findAll();
    }

    public FundArchive updateFundArchive(Long id, FundArchiveDto fundArchiveDto){
        FundArchive existingFundArchive= fundArchiveRepository.findById(id).get();
//        existingCustomer.setCustomerID(customer.getCustomerID());
        existingFundArchive.setFundCode(fundArchiveDto.getFundCode());
        existingFundArchive.setDate(fundArchiveDto.getDate());
        existingFundArchive.setPrice(fundArchiveDto.getPrice());

        return fundArchiveRepository.save(existingFundArchive);
    }

    public void deleteById(Long id){
        fundArchiveRepository.deleteById(id);
    }
}

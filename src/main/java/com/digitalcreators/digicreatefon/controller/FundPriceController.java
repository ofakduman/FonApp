package com.digitalcreators.digicreatefon.controller;

import com.digitalcreators.digicreatefon.dto.FundPriceDto;
import com.digitalcreators.digicreatefon.model.FundPrice;
import com.digitalcreators.digicreatefon.service.FundPriceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/FundsPrices")
@RequiredArgsConstructor
public class FundPriceController {

    private final FundPriceServiceImpl fundPriceService;

    @GetMapping
    public List<FundPrice> getAllFundPrices(){
        return fundPriceService.getAllFundPrices();
    }

    @GetMapping("/{fundCode}")
    public FundPrice getFundPriceByFundCode(@PathVariable String fundCode){
        return fundPriceService.getFundPriceByFundCode(fundCode);

    }

    @PostMapping
    public FundPrice createFundPrice(@RequestBody FundPriceDto fundPriceDto){
//        FundPrice fundPrice=new FundPrice(fundPriceDto);
        return fundPriceService.saveFundPrice(fundPriceDto);
    }


    @PatchMapping("/{id}")
    public FundPrice updateCustomer(@PathVariable Long id, @RequestBody FundPriceDto fundPriceDto){
        return fundPriceService.updateFundPrice(id, fundPriceDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundPrice(@PathVariable Long id){
        fundPriceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


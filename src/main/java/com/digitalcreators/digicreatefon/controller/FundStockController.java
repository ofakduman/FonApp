package com.digitalcreators.digicreatefon.controller;

import com.digitalcreators.digicreatefon.model.FundStock;
import com.digitalcreators.digicreatefon.service.FundStockServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/FundStocks")
@RequiredArgsConstructor
public class FundStockController {

    private final FundStockServiceImpl fundStockService;

    @GetMapping
    public List<FundStock> getAllFundStocks(){
        return fundStockService.getAllFundStocks();
    }

    @GetMapping("/{fundCode}/{customerId}")
    public FundStock getFundStockByFundCodeAndCustomerId(@PathVariable String fundCode, @PathVariable Long customerId){
        return fundStockService.getFundStockByFundCodeAndCustomerId(fundCode,customerId);
    }

//    @PostMapping
//    public FundStock createFundStock(@RequestBody FundStockDto fundStockDto){
//        FundStock fundStock=new FundStock(fundStockDto);
//        return fundStockService.saveFundStock(fundStock);
//    }

//    @PatchMapping("/{id}")
//    public FundStock updateFundStock(@PathVariable Long id, @RequestBody FundStockDto fundStockDto){
//        return fundStockService.updateFundStock(id, fundStockDto);
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundStock(@PathVariable Long id){
        fundStockService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
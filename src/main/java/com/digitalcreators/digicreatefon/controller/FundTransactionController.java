package com.digitalcreators.digicreatefon.controller;

import com.digitalcreators.digicreatefon.dto.FundTransactionDto;
import com.digitalcreators.digicreatefon.dto.SystemDateDto;
import com.digitalcreators.digicreatefon.model.FundTransaction;
import com.digitalcreators.digicreatefon.service.FundTransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/FundTransactions")
@RequiredArgsConstructor
public class FundTransactionController {

    private final FundTransactionServiceImpl fundTransactionService;

    @GetMapping
    public List<FundTransaction> getAllFundTransactions(){
        return fundTransactionService.getAllFundTransactions();
    }

//    @PostMapping
//    public FundTransaction saveFundTransaction(@RequestBody FundTransactionDto fundTransactionDto){
//        FundTransaction fundTransaction=new FundTransaction(fundTransactionDto);
//        return fundTransactionService.saveFundTransaction(fundTransaction);
//    }

    @PostMapping("/newTransaction")
    public FundTransaction createTransaction(@RequestBody FundTransactionDto fundTransactionDto){
        return fundTransactionService.createTransaction(fundTransactionDto);
    }

    @PatchMapping("/{id}")
    public FundTransaction updateFundTransaction(@PathVariable Long id, @RequestBody FundTransactionDto fundTransactionDto){
        return fundTransactionService.updateFundTransaction(id, fundTransactionDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundTransaction(@PathVariable Long id){
        fundTransactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/triggerNextDay")
    public ResponseEntity<Void> triggerSomeProcess(@RequestBody SystemDateDto systemDateDto) {
        fundTransactionService.nextDay(systemDateDto);
        return ResponseEntity.noContent().build();
    }
}

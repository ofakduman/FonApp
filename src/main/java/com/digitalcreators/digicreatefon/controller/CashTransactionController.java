package com.digitalcreators.digicreatefon.controller;

import com.digitalcreators.digicreatefon.dto.CashTransactionDto;
import com.digitalcreators.digicreatefon.dto.SystemDateDto;
import com.digitalcreators.digicreatefon.model.CashTransaction;
import com.digitalcreators.digicreatefon.model.CashTransactionArchive;
import com.digitalcreators.digicreatefon.service.CashTransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api/CashTransactions")
@RequiredArgsConstructor
public class CashTransactionController {

    private final CashTransactionServiceImpl cashTransactionService;

    @GetMapping
    public List<CashTransaction> getAllCashTransactions(){
        return cashTransactionService.getAllCashTransactions();
    }

    @PostMapping("newCashTransaction")
    public CashTransaction createCashTransaction(CashTransactionDto cashTransactionDto){
        return cashTransactionService.createCashTransaction(cashTransactionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCashTransaction(@PathVariable Long id){
        cashTransactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/triggerNextDay")
    public ResponseEntity<Void> triggerSomeProcess(@RequestBody SystemDateDto systemDateDto) {
        cashTransactionService.nextDay(systemDateDto);
        return ResponseEntity.noContent().build();
    }




    @GetMapping("/byDate")
    public List<CashTransactionArchive> getCashTransactionArchiveByDate(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {

        for (int i = 0; i < 10000; i++) {
            System.out.println("Tiryaki");
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date startDate = null;
        java.util.Date endDate = null;

        try {
            startDate = format.parse(startDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            // Tarih formatı yanlışsa bir hata mesajı döndürebilirsiniz
            e.printStackTrace();
            return null;
        }

        return null;
//         return cashTransactionService.getFilteredData(startDate, endDate); // Örnek bir servis çağrısı
    }



}

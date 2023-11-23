package com.digitalcreators.digicreatefon.controller;

import com.digitalcreators.digicreatefon.dto.FundDto;
import com.digitalcreators.digicreatefon.model.Fund;
import com.digitalcreators.digicreatefon.service.FundServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Funds")
@RequiredArgsConstructor
public class FundController {

    @Autowired
    private FundServiceImpl fundService;

    @GetMapping()
    public List<Fund> getAllFunds() {
        return fundService.getAllFunds();
    }

    @GetMapping("/{fundCode}")
    public Fund getFundByFundCode(@PathVariable String fundCode){
        return fundService.getFundByFundCode(fundCode);
    }

    @PostMapping()
    public Fund createFund(@RequestBody FundDto fundDto) {
        Fund fund=new Fund(fundDto);
        return fundService.saveFund(fund);
    }

    @PatchMapping("/{id}")
    public Fund updateFund(@PathVariable Long id, @RequestBody FundDto fundDto){
        return fundService.updateFund(id, fundDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFund(@PathVariable int id) {
        fundService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
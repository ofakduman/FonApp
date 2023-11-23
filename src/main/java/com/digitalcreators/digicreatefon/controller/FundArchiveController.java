package com.digitalcreators.digicreatefon.controller;

import com.digitalcreators.digicreatefon.dto.FundArchiveDto;
import com.digitalcreators.digicreatefon.model.FundArchive;
import com.digitalcreators.digicreatefon.service.FundArchiveServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/FundArchives")
@RequiredArgsConstructor
public class FundArchiveController {

    private final FundArchiveServiceImpl fundArchiveService;

    @GetMapping
    public List<FundArchive> getAllFundArchives(){
        return fundArchiveService.getAllFundArchives();
    }

    @PostMapping
    public FundArchive createFundArchive(@RequestBody FundArchiveDto fundArchiveDto){
        FundArchive fundArchive=new FundArchive(fundArchiveDto);
        return fundArchiveService.saveFundArchive(fundArchive);
    }

    @PatchMapping("/{id}")
    public FundArchive updateFundArchive(@PathVariable Long id, @RequestBody FundArchiveDto fundArchiveDto){
        return fundArchiveService.updateFundArchive(id, fundArchiveDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundArchive(@PathVariable Long id){
        fundArchiveService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.digitalcreators.digicreatefon.controller;

import com.digitalcreators.digicreatefon.model.SystemDate;
import com.digitalcreators.digicreatefon.service.SystemDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/SystemDate")
@RequiredArgsConstructor
public class SystemDateController {

    private final SystemDateService systemDateService;

    @GetMapping
    public List<SystemDate> getAllSystemDate(){
        return systemDateService.gelAllSystemDates();
    }

    @PostMapping
    public SystemDate createSystemDate(@RequestBody SystemDate systemDate){
        return systemDateService.saveSystemDate(systemDate);
    }

    //günü ilerletme servisi gidip systemdatei artırcak archive tablosuna ekleme yapacak. ana tabloyu silecek fundpricetan. post olsun. Diğerlerini sil

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSystemDate(@PathVariable Long id){
        systemDateService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
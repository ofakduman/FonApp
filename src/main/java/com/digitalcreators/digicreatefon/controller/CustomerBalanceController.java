package com.digitalcreators.digicreatefon.controller;

import com.digitalcreators.digicreatefon.model.CustomerBalance;
import com.digitalcreators.digicreatefon.service.CustomerBalanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/CustomerBalances")
@RequiredArgsConstructor
public class CustomerBalanceController {

    private final CustomerBalanceServiceImpl customerBalanceService;

    @GetMapping
    public List <CustomerBalance> getAllCustomerBalances(){
        return customerBalanceService.getAllCustomerBalances();

    }

    @GetMapping("/{customerId}")
    public CustomerBalance getCustomerBalanceByCustomerId(@PathVariable Long customerId ){
        return customerBalanceService.getBalanceByCustomerId(customerId);
    }

//    @PostMapping
//    public CustomerBalance createCustomerBalance(@RequestBody CustomerBalanceDto customerBalanceDto){
//        CustomerBalance customerBalance = new CustomerBalance(customerBalanceDto);
//        return customerBalanceService.saveCustomerBalance(customerBalance);
//    }

//    @PatchMapping("/{id}")
//    public CustomerBalance updateCustomerBalance(@PathVariable Long id, @RequestBody CustomerBalanceDto customerBalanceDto){
//        return customerBalanceService.updateCustomerBalance(id, customerBalanceDto);
//
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerBalance(@PathVariable Long id) {
        customerBalanceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

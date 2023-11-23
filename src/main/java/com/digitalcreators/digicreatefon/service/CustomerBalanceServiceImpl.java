package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.model.CustomerBalance;
import com.digitalcreators.digicreatefon.repository.CustomerBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerBalanceServiceImpl implements CustomerBalanceService{
    private final CustomerBalanceRepository customerBalanceRepository;

    public CustomerBalance saveCustomerBalance(CustomerBalance customerBalance) {
        return customerBalanceRepository.save(customerBalance);
    }

    public List<CustomerBalance> getAllCustomerBalances() {
        return customerBalanceRepository.findAll();
    }

    public CustomerBalance getBalanceByCustomerId(Long customerId){
        CustomerBalance customerBalance=customerBalanceRepository.findByCustomerId(customerId);
        return customerBalance;
    }

//    public CustomerBalance updateCustomerBalance(Long id, CustomerBalanceDto customerBalanceDto){
//        CustomerBalance existingCustomerBalance= customerBalanceRepository.findById(id).get();
//
//        existingCustomerBalance.setCustomerId(customerBalanceDto.getCustomerId());
//        existingCustomerBalance.setBalance(customerBalanceDto.getBalance());
//
//
//        return customerBalanceRepository.save(existingCustomerBalance);
//    }


    public void deleteById(Long id)
    {
        customerBalanceRepository.deleteById(id);
    }
}

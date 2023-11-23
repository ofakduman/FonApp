package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.model.CustomerBalance;

import java.util.List;

public interface CustomerBalanceService {

    public CustomerBalance saveCustomerBalance(CustomerBalance customerBalance) ;

    public List<CustomerBalance> getAllCustomerBalances();

    public CustomerBalance getBalanceByCustomerId(Long customerId);


    public void deleteById(Long id);
}

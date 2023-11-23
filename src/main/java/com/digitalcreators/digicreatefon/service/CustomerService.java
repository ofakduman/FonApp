package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.CustomerDto;
import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.model.CustomerBalance;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface CustomerService {

    public Customer saveCustomer(Customer customer) ;

    public List<Customer> getAllCustomers();

    public boolean updateCustomer(Long id, CustomerDto customerDto);

    public boolean updateCustomer(Long id, Customer customer);

    public void deleteById(Long id);

    public List<Customer> getCustomersByName(String name) ;


    public Customer getCustomerById(Long id);

    public List<Customer> getCustomersBySurname(String surname) ;
}

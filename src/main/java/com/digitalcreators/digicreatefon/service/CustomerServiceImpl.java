package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.CustomerDto;
import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.model.CustomerBalance;
import com.digitalcreators.digicreatefon.repository.CustomerBalanceRepository;
import com.digitalcreators.digicreatefon.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerBalanceRepository customerBalanceRepository;

    public Customer saveCustomer(Customer customer) {
        Customer customer1=customerRepository.save(customer); //customer kaydedilmeden balance oluşmadığı için bu yapılıyor. CustomerBalance customerId'ye ihtiyaç duyuyor.
        CustomerBalance customerBalance=new CustomerBalance(customer1);
        customerBalanceRepository.save(customerBalance);
        return customer1;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public boolean updateCustomer(Long id, CustomerDto customerDto){
        try {
            Customer existingCustomer= customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

            existingCustomer.setName(customerDto.getName());
            existingCustomer.setTCNO(customerDto.getTCNO());
            existingCustomer.setSurname(customerDto.getSurname());
            existingCustomer.setEmail(customerDto.getEmail());
            existingCustomer.setAddress(customerDto.getAddress());
            existingCustomer.setBirthDate(customerDto.getBirthDate());
            existingCustomer.setPhoneNumber(customerDto.getPhoneNumber());
            existingCustomer.setBirthDate(customerDto.getBirthDate());
            existingCustomer.setCity(customerDto.getCity());

            customerRepository.save(existingCustomer);
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean updateCustomer(Long id, Customer _customer) {
        try {
            Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));

            existingCustomer.setName(_customer.getName());
            existingCustomer.setTCNO(_customer.getTCNO());
            existingCustomer.setSurname(_customer.getSurname());
            existingCustomer.setEmail(_customer.getEmail());
            existingCustomer.setAddress(_customer.getAddress());
            existingCustomer.setBirthDate(_customer.getBirthDate());
            existingCustomer.setPhoneNumber(_customer.getPhoneNumber());
            existingCustomer.setBirthDate(_customer.getBirthDate());
            existingCustomer.setCity(_customer.getCity());

            customerRepository.save(existingCustomer);
            return true;
        } catch (Exception e) {

            return false;
        }
    }


    public void deleteById(Long id)
    {
        customerRepository.deleteById(id);
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getCustomersBySurname(String surname) {
        return customerRepository.findBySurnameContainingIgnoreCase(surname);
    }

}

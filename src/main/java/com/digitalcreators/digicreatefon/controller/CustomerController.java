package com.digitalcreators.digicreatefon.controller;

import java.util.List;

import com.digitalcreators.digicreatefon.dto.CustomerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.service.CustomerServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }


    @GetMapping
    public List<Customer> getCustomersByName(@RequestParam(value = "name", required = false) String name) {
        if (name != null && !name.trim().isEmpty()) {
            return customerService.getCustomersByName(name);
        }
        return customerService.getAllCustomers();
    }

    @GetMapping("/bySurname")
    public List<Customer> getCustomersBySurname(@RequestParam(value = "surname", required = false) String surname) {

        if (surname != null && !surname.trim().isEmpty()) {
            return customerService.getCustomersBySurname(surname);}

        return null;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public Customer createCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = new Customer(customerDto);
        return customerService.saveCustomer(customer);
    }

    @PatchMapping("/{id}")
    public boolean updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto){

        return customerService.updateCustomer(id, customerDto);

    }

    @PutMapping("/{id}")
    public boolean updateACustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto){

        return customerService.updateCustomer(id, customerDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/direct")
    public ResponseEntity<Boolean> createDirectCustomer(@RequestBody Customer customer) {
        try {
            System.out.println(customer.toString()); // Veya log.info(customer.toString());



            customerService.saveCustomer(customer);
            return ResponseEntity.ok(true);  // Başarılı bir şekilde kaydedildiyse true döner.

        } catch (Exception e) {
            System.out.println("Bir hata oluştu: " + e.getMessage()); // Veya log.error("Bir hata oluştu: ", e);
            return ResponseEntity.ok(false);  // Hata durumunda false döner.
        }
    }

}
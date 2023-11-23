package com.digitalcreators.digicreatefon.repository;


import com.digitalcreators.digicreatefon.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContainingIgnoreCase(String name);
    List<Customer> findBySurnameContainingIgnoreCase(String surname);


}

package com.digitalcreators.digicreatefon.repository;

import com.digitalcreators.digicreatefon.model.CustomerBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerBalanceRepository extends JpaRepository<CustomerBalance, Long> {
    CustomerBalance findByCustomerId(Long customerId);
}

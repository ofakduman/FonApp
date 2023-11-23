package com.digitalcreators.digicreatefon.repository;

import com.digitalcreators.digicreatefon.model.FundStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundStockRepository extends JpaRepository<FundStock,Long> {

    FundStock findByFundCodeAndCustomerId(String fundCode, Long customerId);
    List<FundStock> findByCustomerId(Long customerId);
}

package com.digitalcreators.digicreatefon.repository;

import com.digitalcreators.digicreatefon.model.FundPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundPriceRepository extends JpaRepository <FundPrice,Long> {

    FundPrice findByFundCode(String fundCode);
}

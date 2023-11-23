package com.digitalcreators.digicreatefon.repository;

import com.digitalcreators.digicreatefon.model.Fund;
import com.digitalcreators.digicreatefon.model.FundStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundRepository extends JpaRepository<Fund,Long> {

    Fund findByFundCode(String fundCode);
    List<Fund> findByFundCodeContainingIgnoreCase(String fundCode);

}

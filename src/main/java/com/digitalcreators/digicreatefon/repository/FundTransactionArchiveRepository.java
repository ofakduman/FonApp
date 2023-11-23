package com.digitalcreators.digicreatefon.repository;

import com.digitalcreators.digicreatefon.model.CashTransactionArchive;
import com.digitalcreators.digicreatefon.model.FundTransactionArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface FundTransactionArchiveRepository extends JpaRepository<FundTransactionArchive, Long> {

    @Query("SELECT c FROM FundTransactionArchive c WHERE c.date BETWEEN :startDate AND :endDate")
    List<FundTransactionArchive> findTransactionsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);



}

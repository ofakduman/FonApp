package com.digitalcreators.digicreatefon.repository;

import com.digitalcreators.digicreatefon.model.CashTransactionArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface CashTransactionArchiveRepository extends JpaRepository<CashTransactionArchive, Long> {
    @Query("SELECT c FROM CashTransactionArchive c WHERE c.date BETWEEN :startDate AND :endDate")
    List<CashTransactionArchive> findTransactionsBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    CashTransactionArchive getCashTransactionArchiveByCashTransactionId(Long cashTransactionId);
}

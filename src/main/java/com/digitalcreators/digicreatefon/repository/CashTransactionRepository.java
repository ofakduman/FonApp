package com.digitalcreators.digicreatefon.repository;

import com.digitalcreators.digicreatefon.model.CashTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashTransactionRepository extends JpaRepository<CashTransaction, Long> {

}

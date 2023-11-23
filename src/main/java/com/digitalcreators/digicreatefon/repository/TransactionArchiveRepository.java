package com.digitalcreators.digicreatefon.repository;

import com.digitalcreators.digicreatefon.model.FundTransactionArchive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionArchiveRepository extends JpaRepository<FundTransactionArchive, Long> {
}

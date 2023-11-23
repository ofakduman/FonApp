package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.CashTransactionDto;
import com.digitalcreators.digicreatefon.dto.GeneralCashTransactionDto;
import com.digitalcreators.digicreatefon.dto.SystemDateDto;
import com.digitalcreators.digicreatefon.model.CashTransaction;
import com.digitalcreators.digicreatefon.model.CashTransactionArchive;
import com.digitalcreators.digicreatefon.model.CustomerBalance;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface CashTransactionService {

    public CashTransaction saveCashTransaction(CashTransaction cashTransaction);

    public CashTransaction createCashTransaction(CashTransactionDto cashTransactionDto);

    public void rollBackTransaction(CashTransaction cashTransaction);

    public void rollBackTransaction(GeneralCashTransactionDto generalCashTransactionDto);

    public void rollBackTransaction(long cash_transaction_id);

    public CashTransactionArchive addCashTransactionToArchive(CashTransaction cashTransaction, SystemDateDto systemDate);

    public List<CashTransaction> getAllCashTransactions();

    public void deleteById(Long id);

    @Transactional
    public void nextDay(SystemDateDto systemDateDto);

    public List<CashTransactionArchive> getFilteredData(Date startDate, Date endDate);

}

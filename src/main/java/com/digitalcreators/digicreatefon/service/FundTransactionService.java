package com.digitalcreators.digicreatefon.service;

import com.digitalcreators.digicreatefon.dto.FundTransactionDto;
import com.digitalcreators.digicreatefon.dto.SystemDateDto;
import com.digitalcreators.digicreatefon.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface FundTransactionService {

    public FundTransaction createTransaction(FundTransactionDto fundTransactionDto);

    public void rollBackTransaction(FundTransaction fundTransaction);

    public FundTransactionArchive addFundTransactionToArchive(FundTransaction fundTransaction, SystemDateDto systemDate);

    public FundTransaction updateFundTransaction(Long id, FundTransactionDto fundTransactionDto);

    public List<FundTransaction> getAllFundTransactions();

    public void deleteById(Long id);

    @Transactional
    public void nextDay(SystemDateDto systemDateDto) ;

    List<FundTransactionArchive> getFilteredFundArchiveData(Date date, Date date1);

}

package com.digitalcreators.digicreatefon.bean;

import com.digitalcreators.digicreatefon.model.FundTransaction;
import com.digitalcreators.digicreatefon.model.FundTransactionArchive;
import com.digitalcreators.digicreatefon.service.FundTransactionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Controller(value = "fundTradeListBean")
public class FundTradeListBean {
    @Autowired
    private FundTransactionService fundTransactionService;


    public List<FundTransactionArchive> filteredFundTransactionArchiveList = new ArrayList<>();
    public FundTransactionArchive selectedFundTransactionArchive;


    Date startDate = new Date();
    Date endDate = new Date();

    public void searchFundTransactions(){
        filteredFundTransactionArchiveList.clear();

        List<FundTransactionArchive> fundTransactionArchives  = fundTransactionService.getFilteredFundArchiveData(new java.sql.Date(startDate.getTime()),new java.sql.Date(endDate.getTime()));

        filteredFundTransactionArchiveList.addAll(fundTransactionArchives);
//        for (int i = 0; i < 100; i++) {
//            System.out.println(filteredFundTransactionArchiveList.size());
//        }
    }

    public void cancelTransaction(FundTransactionArchive canceled_fund_archive){
        FundTransaction canceled_fund = new FundTransaction(canceled_fund_archive);
        this.fundTransactionService.rollBackTransaction(canceled_fund);
    }



}

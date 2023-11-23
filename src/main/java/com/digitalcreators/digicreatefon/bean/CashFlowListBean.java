package com.digitalcreators.digicreatefon.bean;


import com.digitalcreators.digicreatefon.dto.GeneralCashTransactionDto;
import com.digitalcreators.digicreatefon.model.CashTransaction;
import com.digitalcreators.digicreatefon.model.CashTransactionArchive;
import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.model.SystemDate;
import com.digitalcreators.digicreatefon.service.CashTransactionService;
import com.digitalcreators.digicreatefon.service.CashTransactionServiceImpl;
import com.digitalcreators.digicreatefon.service.SystemDateService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.digitalcreators.digicreatefon.utils.Constants.CASH_TRANSACTION_ARCHIVE_BY_DATE;


@Getter
@Setter
@Controller(value = "cashFlowListBean")
public class CashFlowListBean {
    @Autowired
    SystemDateService sds;
    @Autowired
    private CashTransactionService cashTransactionService;

    public Date startDate;
    public Date endDate;
    public List<GeneralCashTransactionDto> filteredGeneralTransactions = new ArrayList<>();
    public CashTransactionArchive selectedCashTransactionArchive;

    public void searchTransactionsByDateRange() {
        filteredGeneralTransactions.clear();
        java.sql.Date sqlStartDate =  new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate =  new java.sql.Date(endDate.getTime());
        List<CashTransactionArchive> cashTransactionArchives = cashTransactionService.getFilteredData(sqlStartDate,sqlEndDate);
        for (CashTransactionArchive a_cash :
                cashTransactionArchives) {
            GeneralCashTransactionDto a_cash_flow = new GeneralCashTransactionDto(a_cash);
            filteredGeneralTransactions.add(a_cash_flow);
        }
//        filteredGeneralTransactions.addAll(cashTransactionArchives);

        SystemDate today =  sds.getLatestSystemDate();

        if( (today.getSystem_date().equals(sqlStartDate) || today.getSystem_date().equals(sqlEndDate))
                || (today.getSystem_date().after(sqlStartDate) && today.getSystem_date().before(sqlEndDate))){
            //bugunu de al
            mergeTodayTransactions();
        }

    }

    public void mergeTodayTransactions(){

        List<CashTransaction> cashTransactions = cashTransactionService.getAllCashTransactions();
        for (CashTransaction a_cash :
                cashTransactions) {
            GeneralCashTransactionDto a_cash_flow = new GeneralCashTransactionDto(a_cash);
            filteredGeneralTransactions.add(a_cash_flow);
        }
    }




    public void cancelTransaction(GeneralCashTransactionDto canceled_transaction){
        cashTransactionService.rollBackTransaction(canceled_transaction);
    }
}


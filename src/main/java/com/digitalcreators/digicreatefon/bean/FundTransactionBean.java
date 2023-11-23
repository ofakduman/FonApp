package com.digitalcreators.digicreatefon.bean;



import com.digitalcreators.digicreatefon.dto.FundTransactionDto;
import com.digitalcreators.digicreatefon.model.*;
import com.digitalcreators.digicreatefon.service.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.util.*;

import static com.digitalcreators.digicreatefon.utils.Constants.*;

@Getter
@Setter
@Controller(value = "fund_transactionBean")
//@Named(value = "fund_transactionBean")
@RequestScope
public class FundTransactionBean {



    @Autowired
    private FundTransactionService fts;

    public void setFts(FundTransactionService fts) {
        this.fts = fts;
    }

    @Autowired
    private FundService fs;

    public void setFs(FundService fs) {
        this.fs = fs;
    }

//    @ManagedProperty(value = "#{FundPriceService}")
    @Autowired
    private FundPriceService fps;

    public void setFps(FundPriceService fps) {
        this.fps = fps;
    }

    @Autowired
    private CustomerBalanceService cbs;

    public void setCbs(CustomerBalanceService cbs) {
        this.cbs = cbs;
    }
    @Autowired
    private FundStockService fss;

    public void setFss(FundStockService fss) {
        this.fss = fss;
    }

    @Autowired
    private CustomerService cs;

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    private static final Logger logger = LoggerFactory.getLogger(PersonnelRegisterBean.class);



    private String routeInput;
    private int route;
    private String fundCode;
    private Long customerId;
    private Long amount;
    private String customerNameSurname;
    private float customerBalance;
    private String fundName;
    private Long fundStockAmount; //stockta kaç tane oldu o
    private float fundPrice;
    private float priceToPay;//fundPrice * amount



    private List<Fund> fundList;
    private List<Customer> customerList;
    private List<String> fundCodes = new ArrayList<>();
    private List<Long> customerIds = new ArrayList<>();
    private List<String> customerNames = new ArrayList<>();
    private List<String> customerSurnames = new ArrayList<>();

    @PostConstruct
    public void init(){

        fundList=fetchFunds();
        customerList=fetchCustomers();


    }





    private List<Fund> fetchFunds(){
        return this.fs.getAllFunds();
    }



    private List<Customer> fetchCustomers(){
        return this.cs.getAllCustomers();
    }


    public void handleBothFundPriceAndAmount(){
        this.listFundPrice();
        this.getFundStockFunc();
    }



    public void getBalanceFunc(){

        CustomerBalance cb = this.cbs.getBalanceByCustomerId(this.customerId);
        this.customerBalance = cb.getBalance();

    }


    public void getFundStockFunc(){
//        FundStock fs = fetchFundStockByFundCode(fundCode, customerId);
//        this.fundStockAmount = fs.getAmount();

        FundStock fundStock = this.fss.getFundStockByFundCodeAndCustomerId(this.fundCode,this.customerId);
        this.fundStockAmount = fundStock.getAmount();

    }


    public void listFundPrice(){
        FundPrice fp = this.fps.getFundPriceByFundCode(this.fundCode);
        this.fundPrice = fp.getCurrentPrice();
    }



    public void createTransaction(){

        try {
            FundTransactionDto fundTransactionDto = constructFundTransaction();
            this.fts.createTransaction(fundTransactionDto);
            handleSuccessfulTransaction(); // Transaction başarılı olduğunda bilgilendirme.
        } catch (Exception e) {
            handleUnexpectedError(e); // Beklenmeyen bir hata olduğunda bilgilendirme.
        }
    }




    private FundTransactionDto constructFundTransaction(){
        if(routeInput.equals("Alış")){
            route= 1;
        }
        else{
            route=0;
        }

        FundTransactionDto fundTransactionDto = new FundTransactionDto();
        fundTransactionDto.setFundcode(this.fundCode);
        fundTransactionDto.setCustomerId(this.customerId);
        fundTransactionDto.setAmount(this.amount);
        fundTransactionDto.setRoute(this.route);

        return fundTransactionDto;


    }


    private String handleSuccessfulTransaction() {
        logger.info("İşlem başarılı.");
        addFacesMessage(FacesMessage.SEVERITY_INFO, "İşlem Başarılı!", "İşlem Başarıyla Tamamlandı.");
        return "home?faces-redirect=true";
    }

    private String handleFailedTransaction() {
        logger.warn("İşlem başarısız.");
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Hata!", "İşlem başarısız.");
        return null;
    }

    private String handleUnexpectedError(Exception e) {
        logger.error("Bir hata oluştu: ", e);
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Hata!", "Beklenmeyen bir hata oluştu.");
        return null;
    }

    private void addFacesMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }



}

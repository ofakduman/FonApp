package com.digitalcreators.digicreatefon.bean;

import com.digitalcreators.digicreatefon.dto.CashTransactionDto;
import com.digitalcreators.digicreatefon.dto.FundTransactionDto;
import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.model.CustomerBalance;
import com.digitalcreators.digicreatefon.service.CashTransactionService;
import com.digitalcreators.digicreatefon.service.CustomerBalanceService;
import com.digitalcreators.digicreatefon.service.CustomerService;
import com.digitalcreators.digicreatefon.service.FundStockService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.digitalcreators.digicreatefon.utils.Constants.*;

@Getter
@Setter
@Named(value = "cash_transactionBean")
@RequestScope
public class CashTransactionBean {

    @Autowired
    private CustomerService cs;

    public void setCs(CustomerService cs) {
        this.cs = cs;
    }

    @Autowired
    private CashTransactionService cts;

    @Autowired
    private CustomerBalanceService cbs;

    public void setCts(CashTransactionService cts) {
        this.cts = cts;
    }

    private static final Logger logger = LoggerFactory.getLogger(PersonnelRegisterBean.class);

    private Long customerId;
    private String routeInput;
    private int route;
    private Long amount;

    private Date date;
    private String nameSurname;
    private float balance;

    private List<Customer> customerList;



    @PostConstruct
    public void init(){

        customerList=fetchCustomers();

    }


//    private HttpHeaders createHttpHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
//        headers.add("Cookie", "JSESSIONID=" + sessionId);
//        return headers;
//    }


//    private List<Customer> fetchCustomers() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = createHttpHeaders();
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        String url = GETALLCUSTOMERS ;
//
//        ResponseEntity<List<Customer>> response = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                entity,
//                new ParameterizedTypeReference<List<Customer>>() {}
//        );
//        return response.getBody();
//    }

    private List<Customer> fetchCustomers() {

        return this.cs.getAllCustomers();

    }

    public void getBalanceFunc(){

        CustomerBalance customerBalance = this.cbs.getBalanceByCustomerId(this.customerId);
        this.balance = customerBalance.getBalance();

    }

//    public String createTransaction() throws IOException {
//
////        try {
//            Map<String, Object> requestBody = constructRequestBody();
//            HttpEntity<Map<String, Object>> entity = prepareHttpEntity(requestBody);
//
//            Boolean transactionResult = callCashTransactionService(entity);
//
//            if (transactionResult) {
//                return handleSuccessfulTransaction();
//            } else {
//                return handleFailedTransaction();
//            }
//
////        }
////        catch (Exception e){
////            return handleUnexpectedError(e);
////        }
//
//    }

    public void createTransaction(){

        CashTransactionDto cashTransactionDto = constructCashTransaction();
        this.cts.createCashTransaction(cashTransactionDto);

    }



    private CashTransactionDto constructCashTransaction(){
        if(routeInput.equals("Alış")){
            route= 1;
        }
        else{
            route=0;
        }

        CashTransactionDto cashTransactionDto = new CashTransactionDto();
        cashTransactionDto.setCustomerId(this.customerId);
        cashTransactionDto.setRoute(this.route);
        cashTransactionDto.setAmount(this.amount);

        return cashTransactionDto;
    }

//    private Map<String, Object> constructRequestBody() {
//        if(routeInput.equals("Alış")){
//            route= 1;
//        }
//        else{
//            route=0;
//        }
//        Map<String, Object> requestBody = new HashMap<>();
//
//        requestBody.put("customerId", customerId);
//        requestBody.put("route", route);
//        requestBody.put("amount", amount);
//
//        return requestBody;
//    }
//
//    private HttpEntity<Map<String, Object>> prepareHttpEntity(Map<String, Object> body) {
//        HttpHeaders headers = createHttpHeaders();
//        return new HttpEntity<>(body, headers);
//    }
//
//    private Boolean callCashTransactionService(HttpEntity<Map<String, Object>> entity) {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Boolean> response= restTemplate.postForEntity(CASHTRANSACTION_URL, entity, Boolean.class);
//        return response.getBody();
//
//
//    }

    private String handleSuccessfulTransaction() {
        logger.info("İşlem başarılı.");
        addFacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı!", "İşlem Başarıyla Tamamlandı.");
        return "home?faces-redirect=true";
    }

    private String handleFailedTransaction() {
        logger.warn("İşlem başarısız.");
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Hata!", "İşlem başarısız.");
        return null;
    }

    private void addFacesMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }






}

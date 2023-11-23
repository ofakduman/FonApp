package com.digitalcreators.digicreatefon.bean;


import com.digitalcreators.digicreatefon.dto.FundDto;
import com.digitalcreators.digicreatefon.dto.FundPriceDto;
import com.digitalcreators.digicreatefon.model.Fund;
import com.digitalcreators.digicreatefon.service.FundPriceService;
import com.digitalcreators.digicreatefon.service.FundService;
import com.digitalcreators.digicreatefon.service.FundServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;

import static com.digitalcreators.digicreatefon.utils.Constants.*;

@Getter
@Setter
@Controller(value = "fund_registerBean")
public class FundRegisterBean {

    @Autowired
    private FundService fs;

    public void setFs(FundService fs) {
        this.fs = fs;
    }

    @Autowired
    private FundPriceService fps;

    public void setFps(FundPriceService fps) {
        this.fps = fps;
    }

    private String fundName;
    private String fundCode;
    private String fundIsinCode;
    private String fundType;
    private int fundPrice;
    private String founderInformation;

    private static final Logger logger = LoggerFactory.getLogger(FundRegisterBean.class);

//    public String registerFund() {
//        try {
//
//            Map<String, Object> requestBodyForFund = constructRequestBodyForFund();
//            HttpEntity<Map<String, Object>> entityFund = prepareHttpEntity(requestBodyForFund);
//
//            Boolean registrationResult = callRegistrationService(entityFund);
//
//
//            if (registrationResult ) {
//                return handleSuccessfulRegistration();
//            } else {
//                return handleFailedRegistration();
//            }
//        } catch (Exception e) {
//            return handleUnexpectedError(e);
//        }
//    }

    public void registerFund(){

        FundDto fundDto = constructFund();
        Fund fund=new Fund(fundDto);
        this.fs.saveFund(fund);

    }

    public FundDto constructFund(){
        FundDto fundDto = new FundDto();
        fundDto.setFundType(this.fundType);
        fundDto.setFundCode(this.fundCode);
        fundDto.setFundName(this.fundName);

        return fundDto;
    }


    private Map<String, Object> constructRequestBodyForFund() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("fundName", fundName);
        requestBody.put("fundCode", fundCode);
        requestBody.put("fundIsınCode", fundIsinCode);
        requestBody.put("fundType", fundType);
        requestBody.put("fundPrice", fundPrice);
        requestBody.put("founderInformation", founderInformation);
        return requestBody;
    }

    public void triggerBothFunc(){
        this.registerFund();
        this.createFundPriceFunc();
    }

    public void createFundPriceFunc(){
        FundPriceDto fundPriceDto = new FundPriceDto();
        fundPriceDto.setFundCode(this.fundCode);
        fundPriceDto.setCurrentPrice(this.fundPrice);
        this.fps.saveFundPrice(fundPriceDto);

    }

//    private Map<String, Object> constructRequestBodyForFundPrice() {
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("fundCode", fundCode);
//        requestBody.put("currentPrice", fundPrice);
//        return requestBody;
//    }



    private HttpEntity<Map<String, Object>> prepareHttpEntity(Map<String, Object> body) {
        HttpHeaders headers = createHttpHeaders();
        return new HttpEntity<>(body, headers);
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        headers.add("Cookie", "JSESSIONID=" + sessionId);
        return headers;
    }

    private Boolean callRegistrationService(HttpEntity<Map<String, Object>> entity) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.postForEntity(REGISTER_FUND_URL, entity, Boolean.class);
        return response.getBody();
    }

//    private Boolean callFundPriceService(HttpEntity<Map<String, Object>> entity) {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Boolean> response = restTemplate.postForEntity(FUNDPRICE_URL, entity, Boolean.class);
//        return response.getBody();
//    }

    private String handleSuccessfulRegistration() {
        logger.info("Kayıt başarılı.");
        addFacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı!", "Kayıt başarıyla tamamlandı.");
        return "home?faces-redirect=true";
    }

    private String handleFailedRegistration() {
        logger.warn("Kayıt başarısız.");
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Hata!", "Kayıt başarısız.");
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

package com.digitalcreators.digicreatefon.bean;


import com.digitalcreators.digicreatefon.model.City;
import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.service.CustomerService;
import com.digitalcreators.digicreatefon.service.CustomerServiceImpl;
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

import static com.digitalcreators.digicreatefon.utils.Constants.REGISTER_CUSTOMER_URL_DIRECT;

@Getter
@Setter
@Controller(value = "customer_registerBean")
public class CustomerRegisterBean {

    @Autowired
    private CustomerService customerService;

    private City city;

    private Customer customer = new Customer();

    private String customerNumber;
    private java.util.Date birthDate;

    private String address;

    private static final Logger logger = LoggerFactory.getLogger(CustomerRegisterBean.class);


    public String customerRegister() {
        try {
            customer.setBirthDate(new java.sql.Date(birthDate.getTime()));
            customer.setCity("a city");
            customerService.saveCustomer(customer);

            // Kayıt başarılı mesajı ekleme
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Kayıt Başarılı", "Kayıt başarılı");
            FacesContext.getCurrentInstance().addMessage(null, message);

            return null;
        } catch (Exception e) {
            logger.error("Müşteri kaydedilirken bir hata oluştu.", e);

            // Hata mesajı ekleme
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Kayıt sırasında bir hata oluştu.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            return null;
        }
    }

}

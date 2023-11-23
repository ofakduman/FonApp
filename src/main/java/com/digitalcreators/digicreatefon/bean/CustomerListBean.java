package com.digitalcreators.digicreatefon.bean;


import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.service.CustomerService;
import com.digitalcreators.digicreatefon.service.CustomerServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;

import static com.digitalcreators.digicreatefon.utils.Constants.*;

//import static com.digitalcreators.digicreatefon.utils.Constants.*;


@Getter
@Setter
@Controller(value = "customerListBean")
@ViewScoped
public class CustomerListBean {

    @Autowired
    private CustomerService customerService;


    private List<Customer> filteredCustomers = new ArrayList<>();
    private Customer selectedCustomer;
    private String searchText;
    Date date = new Date();


    @PostConstruct
    public void init(){
        filteredCustomers = customerService.getAllCustomers();
    }

    public void updateCustomer() {

        Boolean response = customerService.updateCustomer(selectedCustomer.getCustomerID(), selectedCustomer );

        searchCustomers();

        if (response) {
            showMessage("Başarılı!", "Müşteri bilgileri başarıyla güncellendi.", FacesMessage.SEVERITY_INFO);
        } else {
            showMessage("Hata!", "Müşteri bilgileri güncellenemedi. Lütfen tekrar deneyin.", FacesMessage.SEVERITY_ERROR);
        }
    }


    public void searchCustomers() {
        filteredCustomers.clear();

        if (searchText.equals("")){
            filteredCustomers.clear();
            return;
        }
        if(isNumeric(searchText)) {
            Long customerId = Long.parseLong(searchText);
            Customer customer = customerService.getCustomerById(customerId);
            if(customer != null) {
                filteredCustomers.add(customer);
            }
        }
        else{
            List<Customer> customers = customerService.getCustomersBySurname(searchText);
            filteredCustomers.addAll(customers);
        }

    }

    public void editCustomer(Customer customer) {
        System.out.println("Selected customer: " + customer.getName());
        this.selectedCustomer = customer;
    }



    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }


    private void showMessage(String summary, String detail, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}




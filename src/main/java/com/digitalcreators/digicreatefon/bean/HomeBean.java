package com.digitalcreators.digicreatefon.bean;

import com.digitalcreators.digicreatefon.model.SystemDate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.sql.Date;

@Getter
@Setter
@Controller(value = "homeBean")
public class HomeBean {


    public String newAction() {
        return "personnel_registration?faces-redirect=true";
    }

    public void nextDay(){

    }

}

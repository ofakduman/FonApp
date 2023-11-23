package com.digitalcreators.digicreatefon.bean;


import com.digitalcreators.digicreatefon.dto.PersonnelDto;
import com.digitalcreators.digicreatefon.exception.PasswordsDoNotMatchException;
import com.digitalcreators.digicreatefon.service.PersonnelServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.Map;

import static com.digitalcreators.digicreatefon.utils.Constants.REGISTER_PERSONNEL_URL;

@Getter
@Setter
@Controller(value = "personnel_registerBean")
public class PersonnelRegisterBean {

    @Autowired
    private PersonnelServiceImpl personnelService;

    private String name;
    private String surname;
    private String tc;
    private String kullaniciAdi;
    private String password1 ;
    private String password2 ;
    private String email;
    private String gorev;

    private PersonnelDto personnelDto = new PersonnelDto();


    private static final Logger logger = LoggerFactory.getLogger(PersonnelRegisterBean.class);


    public String register() {
        try {
            validatePasswords();
            constructPersonnelDto();

            Boolean isSuccessRegister = this.personnelService.registerPersonnel(personnelDto);

            if (isSuccessRegister) {
                return handleSuccessfulRegistration();
            } else {
                return handleFailedRegistration();
            }
        } catch (PasswordsDoNotMatchException e) {
            return handlePasswordMismatch(e);
        } catch (Exception e) {
            return handleUnexpectedError(e);
        }
    }


    private void constructPersonnelDto(){
        personnelDto.setName(name);
        personnelDto.setSurname(surname);
        personnelDto.setTcNo(tc);
        personnelDto.setPersonnelUsername(kullaniciAdi);
        personnelDto.setMail(email);
        personnelDto.setPersonnelDuty(gorev);
        personnelDto.setHashedPassword(password1);
    }


    private String handleSuccessfulRegistration() {
        logger.info("Kayıt başarılı.");
        addFacesMessage(FacesMessage.SEVERITY_INFO, "Kayıt Başarılı!", "Kayıt başarıyla tamamlandı.");
        return null;
    }

    private String handleFailedRegistration() {
        logger.warn("Kayıt başarısız.");
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Hata!", "Kayıt başarısız.");
        return null;
    }

    private String handlePasswordMismatch(PasswordsDoNotMatchException e) {
        logger.warn(e.getMessage());
        addFacesMessage(FacesMessage.SEVERITY_ERROR, "Hata!", e.getMessage());
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

    private void validatePasswords() {
        if (!password1.equals(password2)) {
            throw new PasswordsDoNotMatchException("Şifreler eşleşmiyor!");
        }
    }
}




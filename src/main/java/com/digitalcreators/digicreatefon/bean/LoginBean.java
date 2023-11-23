package com.digitalcreators.digicreatefon.bean;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.digitalcreators.digicreatefon.utils.Constants.AUTHENTICATE_URL;

@Getter
@Setter

@Controller(value = "loginBean")
public class LoginBean {
    private static final Logger logger = LoggerFactory.getLogger(LoginBean.class);



    @Getter
    @Setter
    public static class LoginRequest {
        private String username;
        private String password;
    }

    private String username;
    private String password;

    public String login() {
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeaders nesnesini oluşturma
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Oluşturulan request nesnesi
        Map<String, Object> request = new HashMap<>();

        request.put("username", username);
        request.put("password", password);

        logger.error(username);
        logger.error(password);
        if (username == null || password == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,"Kullanıcı Adı ve Şifre Boş Olamaz!", "Kullanıcı Adı ve Şifre Boş Olamaz!");
            FacesContext.getCurrentInstance().addMessage(null,message);
            logger.warn("Username veya password null olarak geldi. Username: {}, Password: {}", username, password);
            return null; // Bu durumda oturum açma işlemini yapmayın ve kullanıcıya uygun bir hata mesajı gösterin.
        }

        // HttpEntity kullanarak body ve headers'ı set etme
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Boolean> response = restTemplate.postForEntity(AUTHENTICATE_URL, entity, Boolean.class);
        if (response == null || response.getBody() == null) {
            logger.error("Rest yanıtı veya yanıt gövdesi null. Response: {}", response);
            return null; // Bu durumda oturum açma işlemini yapmayın ve kullanıcıya uygun bir hata mesajı gösterin.
        }
        boolean isAuthenticated = response.getBody();


        if (isAuthenticated) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Giriş Başarılı", "Hoşgeldiniz, " + username);
            FacesContext.getCurrentInstance().addMessage(null, message);
            logger.info("Kullanıcı başarılı bir şekilde giriş yaptı: {}", username);  // Logger ile mesaj yazdırma

            return "/home?faces-redirect=true"; // Başarılı oturum açma durumunda ana sayfaya yönlendirme
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Giriş Başarısız", "Kullanıcı adı veya parola yanlış.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            logger.warn("Kullanıcı giriş yapamadı. Kullanıcı adı: {}", username, password);  // Logger ile mesaj yazdırma
            return null; // Oturum açma başarısız olduğunda aynı sayfada kal
        }
    }

    public String getUsername() {
        return username;
    }


}

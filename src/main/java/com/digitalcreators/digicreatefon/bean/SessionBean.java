package com.digitalcreators.digicreatefon.bean;

import org.springframework.stereotype.Controller;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

import static io.swagger.v3.oas.integration.StringOpenApiConfigurationLoader.LOGGER;


@Controller(value = "sessionBean")
public class SessionBean {

    // Diğer bean özellikleri veya fonksiyonları burada yer alabilir.

    public void logout() throws IOException {

        try {
            LOGGER.info("Logout method called.");
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            // Oturumu geçersiz kıl
            externalContext.invalidateSession();
            LOGGER.info("Session invalidated.");

            // Giriş sayfasına yönlendir
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
            LOGGER.info("Redirected to login page.");
        } catch (Exception e) {
            LOGGER.error("Error occurred in logout method: " + e.getMessage());
        }
    }


}

package com.digitalcreators.digicreatefon.bean;


import com.digitalcreators.digicreatefon.model.Customer;
import com.digitalcreators.digicreatefon.model.Personnel;
import com.digitalcreators.digicreatefon.service.PersonnelService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.digitalcreators.digicreatefon.utils.Constants.*;

@Getter
@Setter
@Controller(value = "personnelListBean")
@ViewScoped
public class PersonnelListBean {

    @Autowired
    PersonnelService personnelService;

    private List<Personnel> filteredPersonals = new ArrayList<>();
    private Personnel selectedPersonnel = new Personnel();
    private String searchText = "";
    private String plain_password = "";
    private String plain_password2 = "";
    private String new_username = "";
    private String hashed_password = "";
    private boolean username_changed = false;



    @PostConstruct
    public void init(){
        filteredPersonals = personnelService.getAllPersonnels();
    }

    public void searchPersonals() {
        filteredPersonals.clear();
        if (searchText.equals("")){
            filteredPersonals.clear();
            return;
        }
        List<Personnel> personals = (List<Personnel>) personnelService.getPersonalsByPersonnelUsername(searchText);
        filteredPersonals.addAll(personals);
    }

    public boolean canEditUsername(Personnel personnel) {
        if (!(isNotAdmin()) && "admin".equals(personnel.getPersonnelUsername())) {
            return false;
        }
        return true;
    }

    public void updatePersonnel(){
        // Şifrenin boş olmadığını kontrol et
        if (!isPasswordEmpty()) {
            // Şifrelerin eşleşip eşleşmediğini kontrol et
            if (!isPasswordsMatch()) {
                // JSF ile kullanıcıya hata mesajı gönder
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Şifreler eşleşmiyor!"));
                return;
            } else {
                // yeni sifreyi hashle
                String hashed_pass = personnelService.hashPassword(plain_password);
                selectedPersonnel.setHashedPassword(hashed_pass);
            }
        }

        boolean usernameWasChanged = false;
        if (usernameChanged()){
            if (isUsernameAvailable(new_username)){
                //username degisir
                usernameWasChanged = true;
                selectedPersonnel.setPersonnelUsername(new_username);
            } else {
                //username degisemez hata mesaji ver
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kullanıcı Adı Önceden Alınmış", "Kullanıcı adı mevcut!"));
                return;
            }
        }
        updatePersonnel(selectedPersonnel.getPersonnelid(), selectedPersonnel);
    }

    private Personnel updatePersonnel(Long id, Personnel personnel) {
        try {
            personnelService.updatePersonnel(id, personnel);

            // Başarılı güncelleme mesajı ekleme
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Başarılı", "Personel bilgileri başarıyla güncellendi.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            return null;
        } catch (Exception e) {
            // Eğer kodda bir hata oluşursa loglama yapılır ve hata mesajı eklenir.
            //logger.error("Personel bilgileri güncellenirken bir hata oluştu.", e);

            // Hata mesajı ekleme
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hata", "Personel bilgileri güncellenirken bir hata oluştu.");
            FacesContext.getCurrentInstance().addMessage(null, message);

            return null;
        }
    }

    boolean usernameChanged() {
        String currentUsername = selectedPersonnel.getPersonnelUsername();
        // Eğer yeni kullanıcı adı boş değilse ve mevcut kullanıcı adından farklıysa
        if (new_username != null && !new_username.trim().isEmpty() && !new_username.equals(currentUsername)) {
            return true;
        }
        return false;
    }

    boolean isUsernameAvailable(String username){
        return personnelService.isUsernameAvailable(username);
    }

    private boolean isPasswordsMatch() {
        if (plain_password == null || plain_password2 == null) {
            return false;
        }
        return plain_password.equals(plain_password2);
    }

    private boolean isPasswordEmpty(){
        return (plain_password == null || plain_password2 == null);
    }

    public void editPersonnel(Personnel personnel) {
        this.selectedPersonnel = personnel;
    }

    public boolean isNotAdmin() {
        String currentUsername = getCurrentUsername();
        return !currentUsername.equals("admin");
    }

    public String getCurrentUsername() {

        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
    }

    public boolean shouldShowButtonForPersonnel(Personnel currentPersonnel) {
        String currentUsername = getCurrentUsername();

        if ("admin".equals(currentUsername)) {
            return true;
        } else {
            return currentUsername != null && currentUsername.equals(currentPersonnel.getPersonnelUsername());
        }
    }

}




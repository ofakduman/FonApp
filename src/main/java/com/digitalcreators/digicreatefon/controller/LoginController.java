//package com.digitalcreators.digicreatefon.controller;
//
//import com.digitalcreators.digicreatefon.bean.LoginBean;
//import com.digitalcreators.digicreatefon.service.PersonnelService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.ui.Model;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Controller
//@RequestMapping("/login")
//public class LoginController {
//
//    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
//
//    @Autowired
//    private PersonnelService personnelService;
//
//    @GetMapping
//    public String showLoginPage() {
//        logger.info("Login page is being displayed.");
//        return "login.xhtml";
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public String handleLogin(@RequestBody LoginBean.LoginRequest loginRequest) {
//        logger.debug("Attempting to authenticate user: {}", loginRequest.getUsername());
//
//        boolean isAuthenticated = personnelService.authenticatePersonnel(loginRequest.getUsername(), loginRequest.getPassword());
//
//        if (isAuthenticated) {
//            logger.info("User {} authenticated successfully.", loginRequest.getUsername());
//            return "redirect:/home.xhtml"; // Başarılı giriş durumunda "home.xhtml" sayfasına yönlendir.
//        } else {
//            logger.warn("Failed to authenticate user: {}", loginRequest.getUsername());
//            return "login.xhtml"; // Başarısız giriş durumunda login sayfasına geri dön.
//        }
//    }
//}


//
//
//
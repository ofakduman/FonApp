package com.digitalcreators.digicreatefon.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        // Özel yönlendirme mantığınızı burada tanımlayabilirsiniz.
        // Örneğin, aşağıda kullanıcının her zaman "/home.xhtml" sayfasına yönlendirilmesi sağlanmıştır.
        redirectStrategy.sendRedirect(request, response, "/home.xhtml");
    }
}

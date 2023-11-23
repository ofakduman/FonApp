package com.digitalcreators.digicreatefon.config;

import com.digitalcreators.digicreatefon.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/javax.faces.resource/**").permitAll() // PrimeFaces kaynaklarına erişime izin ver
                .antMatchers("/login.xhtml").permitAll() // Giriş sayfasına erişime izin ver
                .antMatchers("/**").hasAnyRole("admin", "personnel") // admin veya personnel rolüne sahip kullanıcılara izin ver
                .antMatchers("/resources/**").permitAll() // Diğer statik kaynaklara erişime izin ver (eğer uygulamanızda varsa)
                .and()
                .formLogin()
                .loginPage("/login.xhtml")
                .defaultSuccessUrl("/home.xhtml",true)
                .failureUrl("/login.xhtml?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Logout işlemi için hangi URL'nin çağrılacağını belirler.
                .invalidateHttpSession(true) // HTTP oturumunu geçersiz kılar.
                .clearAuthentication(true) // Kimlik doğrulama bilgilerini temizler.
                .permitAll()
                .logoutSuccessUrl("/login.xhtml")
                .deleteCookies("JSESSIONID")
                .and();
    }
}

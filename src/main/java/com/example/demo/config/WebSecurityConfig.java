package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.constant.UrlConst;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;
    
    private final MessageSource messageSource;

    private static final String USERNAME_PARAMETER = "loginId";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        .authorizeHttpRequests(
            authrize -> authrize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll().anyRequest().authenticated()
        )
        .formLogin(
            login -> login.loginPage(UrlConst.LOGIN)
                .usernameParameter(USERNAME_PARAMETER)
                .defaultSuccessUrl(UrlConst.MENU))
        .logout(logout -> logout.logoutSuccessUrl(UrlConst.SIGNUP));

        return http.build();
    }

    @Bean
    AuthenticationProvider dauthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        provider.setMessageSource(messageSource);
        return provider;
    }
}

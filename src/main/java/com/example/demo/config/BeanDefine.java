package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.dozer.Mapper;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;

@Configuration
public class BeanDefine {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    Mapper mapper() {
        return new DozerBeanMapper();
    }

}

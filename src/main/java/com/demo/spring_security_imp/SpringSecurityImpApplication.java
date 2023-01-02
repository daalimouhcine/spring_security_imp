package com.demo.spring_security_imp;

import com.demo.spring_security_imp.DATA.AuthUsers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityImpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityImpApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthUsers authUsers() {
        return new AuthUsers();
    }

}

package com.demo.spring_security_imp.shaired.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private final String role = "USER";
}

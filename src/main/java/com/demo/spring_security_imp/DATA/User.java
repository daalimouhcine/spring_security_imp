package com.demo.spring_security_imp.DATA;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String userName;
    private String email;
    private String password;
}

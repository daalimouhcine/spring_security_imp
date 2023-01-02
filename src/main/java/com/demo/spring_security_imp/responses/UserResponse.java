package com.demo.spring_security_imp.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String userName;
    private String email;
    private String password;

}

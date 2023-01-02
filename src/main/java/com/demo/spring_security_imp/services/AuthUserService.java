package com.demo.spring_security_imp.services;

import com.demo.spring_security_imp.shaired.dto.UserDto;

import java.util.ArrayList;

public interface AuthUserService {
    public UserDto registerUser(UserDto userDto);
    public ArrayList<UserDto> getAllUsers();
}

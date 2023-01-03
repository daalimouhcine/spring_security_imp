package com.demo.spring_security_imp.services;

import com.demo.spring_security_imp.DATA.User;
import com.demo.spring_security_imp.shaired.dto.UserDto;

import java.util.ArrayList;

public interface AuthUserService {
    public UserDto registerUser(UserDto userDto);
    public ArrayList<UserDto> getAllUsers();
    public UserDto findByEmail(String email);
    public UserDto loginUser(UserDto userDto);
}

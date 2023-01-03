package com.demo.spring_security_imp.servicesImp;

import com.demo.spring_security_imp.DATA.AuthUsers;
import com.demo.spring_security_imp.DATA.User;
//import com.demo.spring_security_imp.repositories.AuthUserRepository;
import com.demo.spring_security_imp.services.AuthUserService;
import com.demo.spring_security_imp.shaired.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthUserServiceImp implements AuthUserService {
//    @Autowired
//    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthUsers authUsers;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User createdUser = authUsers.addUser(user);
        UserDto createdUserDto = new UserDto();
        BeanUtils.copyProperties(createdUser, createdUserDto);
        return createdUserDto;
    }

    @Override
    public ArrayList<UserDto> getAllUsers() {
        ArrayList<User> users = authUsers.getAllUsers();
        ArrayList<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public UserDto loginUser(UserDto userDto) {
        User user = authUsers.findByEmail(userDto.getEmail());
        if (user != null) {
            if (passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
                UserDto loggedInUserDto = new UserDto();
                BeanUtils.copyProperties(user, loggedInUserDto);
                return loggedInUserDto;
            }
        }
        return null;
    }

    @Override
    public UserDto findByEmail(String email) {
        UserDto userDto = new UserDto();
        User user = authUsers.findByEmail(email);
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}

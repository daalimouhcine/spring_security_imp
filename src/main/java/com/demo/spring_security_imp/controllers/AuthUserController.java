package com.demo.spring_security_imp.controllers;

import com.demo.spring_security_imp.requests.UserRequest;
import com.demo.spring_security_imp.responses.UserResponse;
import com.demo.spring_security_imp.services.AuthUserService;
import com.demo.spring_security_imp.shaired.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
public class AuthUserController {
    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        UserDto createdUser = authUserService.registerUser(userDto);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(createdUser, userResponse);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<ArrayList<UserResponse>> getAllUsers() {
        ArrayList<UserDto> userDtos = authUserService.getAllUsers();
        ArrayList<UserResponse> userResponses = new ArrayList<>();
        for (UserDto userDto : userDtos) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(userDto, userResponse);
            userResponses.add(userResponse);
        }
        return new ResponseEntity<ArrayList<UserResponse>>(userResponses, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        UserDto loggedInUser = authUserService.loginUser(userDto);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(loggedInUser, userResponse);
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }
}

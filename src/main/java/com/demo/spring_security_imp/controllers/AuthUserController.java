package com.demo.spring_security_imp.controllers;

import com.demo.spring_security_imp.helpers.JwtUtils;
import com.demo.spring_security_imp.requests.UserRequest;
import com.demo.spring_security_imp.responses.UserResponse;
import com.demo.spring_security_imp.services.AuthUserService;
import com.demo.spring_security_imp.shaired.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthUserController {
    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private UserDetails userDetails;

    @Autowired
    private JwtUtils jwtUtils;


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
        System.out.println("this is login learner");
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        UserDto loggedInUser = authUserService.loginUser(userDto);
        UserResponse userResponse = new UserResponse();
        System.out.println(loggedInUser);
        BeanUtils.copyProperties(loggedInUser, userResponse);
        if(userResponse != null) {
            System.out.println("user is not null");
            authenticationManager(userDto.getEmail(), userDto.getPassword());
            final UserDetails userDetails = new User(userDto.getEmail(), userDto.getPassword(), Collections.singleton(new SimpleGrantedAuthority(userDto.getRole())));
            System.out.println("user email" + userDetails.getUsername());
            userResponse.setToken(jwtUtils.generateToken(userDetails));
            System.out.println(userResponse.getToken());
            return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void authenticationManager(String email, String password) {
        System.out.println("this is setAuthenticationManager");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }
}

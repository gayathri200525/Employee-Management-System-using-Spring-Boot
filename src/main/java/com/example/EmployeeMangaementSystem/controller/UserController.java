package com.example.EmployeeMangaementSystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeMangaementSystem.dto.RegisterRequest;
import com.example.EmployeeMangaementSystem.dto.VerifyOtpRequest;
import com.example.EmployeeMangaementSystem.service.OtpService;
import com.example.EmployeeMangaementSystem.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;
    private OtpService otpService;

    public UserController(UserService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
    	return otpService.verifyOtp(verifyOtpRequest);
    }
}

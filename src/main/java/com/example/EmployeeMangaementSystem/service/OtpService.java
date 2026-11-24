package com.example.EmployeeMangaementSystem.service;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.EmployeeMangaementSystem.dto.VerifyOtpRequest;
import com.example.EmployeeMangaementSystem.entity.User;
import com.example.EmployeeMangaementSystem.exception.InvalidOtpException;
import com.example.EmployeeMangaementSystem.exception.OtpExpiredException;
import com.example.EmployeeMangaementSystem.exception.OtpNotFoundException;
import com.example.EmployeeMangaementSystem.repository.UserRepository;

@Service
public class OtpService {

    private UserRepository userRepository;

    public OtpService(UserRepository userRepository) {
	        this.userRepository = userRepository;
    }
    
    public String verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(verifyOtpRequest.getEmail());
        if (optionalUser.isEmpty()) {
            return "User not found";
        }
        User user = optionalUser.get();
        if (user.isVerified()) {
            return "OTP already verified";
        }
        if (user.getOtp() == null) {
            throw new OtpNotFoundException("OTP not found");
        }
        if (user.getOtpexpirytime() == null ||
                LocalDateTime.now().isAfter(user.getOtpexpirytime())) {
            throw new OtpExpiredException("OTP expired");
        }
        if (!user.getOtp().equals(verifyOtpRequest.getOtp())) {
            throw new InvalidOtpException("Invalid OTP");
        }
        
        user.setVerified(true);
        user.setOtp(null);
        user.setOtpexpirytime(null);

        userRepository.save(user);

        return "OTP verified successfully";
    }
//	public String verifyOtp(VerifyOtpRequest verifyOtpRequest) {
//         Optional<User> optionalUser = userRepository.findByEmail(verifyOtpRequest.getEmail());
//	        if (optionalUser.isPresent()) {
//	            User user = optionalUser.get();
//	        if (!user.getOtp().equals(verifyOtpRequest.getOtp())) {
//	            return "invalid otp";
//	           }
//
//	        if (LocalDateTime.now().isAfter(user.getOtpexpirytime())) {
//	               return "otp expired";
//	            } else {
//	                user.setVerified(true);
//	                user.setOtp(null);
//	                user.setOtpexpirytime(null);
//	                userRepository.save(user); 
//	                return "otp verified successfully";
//	            }
//	        } else {
//	            return "user not found";
//	        }
//	    }
    

}

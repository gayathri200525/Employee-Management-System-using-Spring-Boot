package com.example.EmployeeMangaementSystem.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.EmployeeMangaementSystem.dto.RegisterRequest;
import com.example.EmployeeMangaementSystem.entity.User;
import com.example.EmployeeMangaementSystem.repository.UserRepository;
import com.example.EmployeeMangaementSystem.util.OtpGenerator;

@Service
public class UserService {

	private UserRepository userRepository;
	private EmailService emailService;
	private PasswordEncoder encoder;
	
	public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.encoder = encoder;
	}

	public String register(RegisterRequest registerRequest) {
		Optional<User> ou = userRepository.findByEmail(registerRequest.getEmail());
		if (ou.isPresent()) {
			return "email id is already exists";
		} else {
			User user = new User();
			user.setName(registerRequest.getName());
			user.setEmail(registerRequest.getEmail());
			user.setPassword(encoder.encode(registerRequest.getPassword()));
			user.setRole("USER");
			user.setUsername(registerRequest.getUsername());
			user.setVerified(false);
			String Otp = OtpGenerator.getopt();
			user.setOtp(Otp);
			user.setOtpexpirytime(LocalDateTime.now().plusMinutes(5));
			userRepository.save(user);

			emailService.sendOtp(registerRequest.getEmail(), Otp);
			
			return "OTP sent successfully to your email";
		}

	}
}


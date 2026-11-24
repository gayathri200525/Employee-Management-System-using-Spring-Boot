package com.example.EmployeeMangaementSystem.config;

import java.util.Optional;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.EmployeeMangaementSystem.entity.User;
import com.example.EmployeeMangaementSystem.repository.UserRepository;

@Service
public class CustomerUserDetails implements UserDetailsService{

	UserRepository userRepository;
	
	public CustomerUserDetails(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> o = userRepository.findByUsername(username);
		User db = o.get();
		UserDetails details = org.springframework.security.core.userdetails.User.builder().username(db.getUsername())
                .password(db.getPassword()).roles(db.getRole()).build();
		return details;
	}
}


//package com.example.EmployeeMangaementSystem.config;
//
//import java.util.Optional;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.EmployeeMangaementSystem.entity.User;
//import com.example.EmployeeMangaementSystem.repository.UserRepository;
//
//@Service
//public class CustomerUserDetails implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomerUserDetails(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<User> o = userRepository.findByUsername(username);
//
//        User db = o.orElseThrow(() ->
//                new UsernameNotFoundException("User not found: " + username));
//
//        UserDetails details = org.springframework.security.core.userdetails.User.builder()
//                .username(db.getUsername())
//                .password(db.getPassword())
//                .roles(db.getRole())
//                .build();
//
//        return details;
//    }
//}
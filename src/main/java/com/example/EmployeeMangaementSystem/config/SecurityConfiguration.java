package com.example.EmployeeMangaementSystem.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
		httpSecurity
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/register", "/api/users/verify-otp") .permitAll()
	    .requestMatchers(HttpMethod.GET, "/employees").hasAnyRole("ADMIN","USER")
	    .requestMatchers("/employees").hasRole("ADMIN")
	    .anyRequest() .authenticated())
		.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//		UserDetails u1 = User.withUsername("user1").password(encoder.encode("user1@123")).roles("USER").build();
//		UserDetails u2 = User.withUsername("user2").password(encoder.encode("user2@123")).roles("ADMIN").build();
//		return new InMemoryUserDetailsManager(u1,u2);
//	}
}

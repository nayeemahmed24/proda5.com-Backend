package com.proda5.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.proda5.repository.RoleRepository;
import com.proda5.repository.UserRepository;
import com.proda5.security.jwt.JwtProvider;


@RestController
public class TestRestAPIs {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	
	
	
 
 @GetMapping("/api/test/patient")
 @PreAuthorize("hasRole('PATIENT')")
 public String userAccess() {
   return ">>> PATIENT Contents!";
 }
 
 @GetMapping("/api/test/doctor")
 @PreAuthorize("hasRole('PATIENT')")
 public String projectManagementAccess() {
	 UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
             .getPrincipal();
	 return userDetails.getUsername();
 }
}
 


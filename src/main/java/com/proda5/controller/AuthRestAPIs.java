package com.proda5.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.proda5.message.request.LoginForm;
import com.proda5.message.request.UserRequest;
import com.proda5.model.UserAccount;
import com.proda5.model.Role;
import com.proda5.model.RoleName;
import com.proda5.model.User;
import com.proda5.repository.RoleRepository;
import com.proda5.repository.UserAccountRepository;
import com.proda5.repository.UserRepository;
import com.proda5.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

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
	
	@Autowired
	UserAccountRepository userAccountRepository;


	
	
	

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getPhonenumber(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return ResponseEntity.ok(jwtProvider.generateJwtToken(authentication));
	}


	@PostMapping("/signup")
	public ResponseEntity<String> registerDoctor(@RequestBody UserRequest signUpRequest) {
		if (userRepository.existsByPhonenumber(signUpRequest.getPhonenumber())) {
			return new ResponseEntity<String>("Fail -> Phone Number is already taken!", HttpStatus.BAD_REQUEST);
		}

		

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getPhonenumber(),signUpRequest.getEmail(), 
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role doctorRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(doctorRole);

				break;
			case "shopowner":
				Role userRole = roleRepository.findByName(RoleName.ROLE_SHOPOWNER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
		});
		
		//////////// ================= Image Save ====================///////////////
		
		
		
		user.setRoles(roles);
		userRepository.save(user);

		 UserAccount userA = new UserAccount(signUpRequest.getName(),signUpRequest.getPhonenumber(),signUpRequest.getEmail(),signUpRequest.getShopname(),signUpRequest.getType(),signUpRequest.getStatus());

		userAccountRepository.save(userA);
		return ResponseEntity.ok().body("User registered successfully!");
	}

}

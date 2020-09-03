package com.lab.vn.labtraining.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.vn.labtraining.model.LoginRequest;
import com.lab.vn.labtraining.model.LoginRequestDTO;
import com.lab.vn.labtraining.model.LoginResponse;
import com.lab.vn.labtraining.model.User;
import com.lab.vn.labtraining.security.CustomUserDetails;
import com.lab.vn.labtraining.security.JwtTokenProvider;
import com.lab.vn.labtraining.services.UserServices;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController extends BaseController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserServices userRepository;

	@Autowired
	private JwtTokenProvider tokenProvider;

	// ---generate jwt---
	@PostMapping("/login")
	public LoginResponse authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

		// Xác thực từ username và password từ bảng LAB_USER : admin:admin
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Trả về jwt cho người dùng.
		String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
		log.info("MA XAC THUC THEO USER GENERATE : " + jwt);
		return new LoginResponse("Bearer " + jwt);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid LoginRequest loginRequest) {
		if (userRepository.existsByUsername(loginRequest.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getStatus(),
				loginRequest.getRole());

//		user.setPassword(passwordEncoder.encode(user.getPassword()));

//		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//				.orElseThrow(() -> new AppException("User Role not set."));
//
//		user.setRoles(Collections.singleton(userRole));

		userRepository.addUser(user);
		return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);

	}

	// Api /api/checkJwt yêu cầu phải xác thực mới có thể request
	@GetMapping("/checkJwt")
//	@Secured("ROLE_ADMIN")
	public String randomStuff() {
		return "JWT Hợp lệ mới có thể thấy được message này";
	}
}

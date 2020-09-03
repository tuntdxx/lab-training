package com.lab.vn.labtraining.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lab.vn.labtraining.controller.BaseController;
import com.lab.vn.labtraining.model.User;
import com.lab.vn.labtraining.repository.UserRepository;
import com.lab.vn.labtraining.security.CustomUserDetails;

@Service
//Khi người dùng đăng nhập thì Spring Security sẽ cần lấy các thông tin UserDetails hiện có để kiểm tra.
//Vì vậy, bạn cần tạo ra một class kế thừa lớp UserDetailsService mà Spring Security cung cấp để làm nhiệm vụ này.
public class UserServices extends BaseController implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Kiem tra user
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		} else {
			// Ma hoa password theo passwordEncoder
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			log.info("LOGIN WITH User : " + username + " PasswordEncoder: " + user.getPassword());
		}
		return new CustomUserDetails(user);
	}

	public boolean existsByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (null != user) {
			return true;
		} else {
			return false;
		}
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	// JWTAuthenticationFilter sẽ sử dụng hàm này
	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
//		Set<Role> s = user.getRole();
//		System.out.println("check_role : " + s);
		return new CustomUserDetails(user);
	}
}

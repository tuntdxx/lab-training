//package com.lab.vn.labtraining.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.lab.vn.labtraining.services.UserServices;
//
// Neu su dung cai nay (Spring security) thi phai comment file filler vs file jwtConfig
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	PasswordEncoder passwordEncoder;
//
//	@Autowired
//	UserServices userServices;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// Test khi ko can connect den DB
////		auth.inMemoryAuthentication()
////				.passwordEncoder(passwordEncoder).withUser("user")
////				.password(passwordEncoder.encode("user")).roles("USER")	// Phân quyền là người dùng
////				.and().withUser("admin")
////				.password(passwordEncoder.encode("admin")).roles("USER", "ADMIN"); // phân quyền là người dùng, người quản lý <2 quyền>
//
//		// Test khi connect voi DB
//		auth.userDetailsService(userServices) // Cung cáp userservice cho spring security
//				.passwordEncoder(passwordEncoder()); // cung cấp password encoder
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/", "/index").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa
//																		// chỉ này
//				.anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
//				.and().formLogin() // Cho phép người dùng xác thực bằng form login
//				.defaultSuccessUrl("/hello").permitAll() // Tất cả đều được truy cập vào địa chỉ này
//				.and().logout() // Cho phép logout
//				.permitAll();
//	}
//}

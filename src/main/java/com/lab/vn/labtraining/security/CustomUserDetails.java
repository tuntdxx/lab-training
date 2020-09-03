package com.lab.vn.labtraining.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lab.vn.labtraining.model.Role;
import com.lab.vn.labtraining.model.User;
import com.lab.vn.labtraining.repository.UserRepository;

//Mặc định Spring Security sử dụng một đối tượng UserDetails để chứa toàn bộ thông tin về người dùng.
//Vì vậy, chúng ta cần tạo ra một class mới giúp chuyển các thông tin của User thành UserDetails.
// Phải tạo getter,setter và constructor có tham số như dưới đây.
public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6076978330115135713L;

	User user;

	@Autowired
	UserRepository userRepo;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		 set 1 quyen role_user cho tat ca cac user
//		return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));

		/**
		 * set role theo User login
		 */
		Set<Role> r = user.getRole();
		System.out.println("Size list role : " + r.size());

		List<String> myList = user.getRole().stream().map(roles -> "ROLE_" + roles.getName())
				.collect(Collectors.toList());
		System.out.println("List Role : " + myList);
//
//		// get 1 list role theo user, hoac 1 list group?
		return user.getRole().stream().map(roles -> new SimpleGrantedAuthority("ROLE_" + roles.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

package com.lab.vn.labtraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.vn.labtraining.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);

}

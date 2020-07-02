package com.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUserName(String userName);
}

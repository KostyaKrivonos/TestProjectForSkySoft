package com.example.userProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.userProject.model.User;
/**
 * I use jpa repository for working with database
 * @author kostyadev
 *
 */
@Component
public interface UserRepository extends JpaRepository<User, Integer> {
	void deleteById(Integer id);
}

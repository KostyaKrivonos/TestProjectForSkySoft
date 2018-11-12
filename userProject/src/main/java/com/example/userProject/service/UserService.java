package com.example.userProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.example.userProject.model.User;
import com.example.userProject.model.dto.UserDto;
import com.example.userProject.repository.UserRepository;

@Component
public class UserService {
	
	private UserRepository userRepository;
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	/**
	 * method creates a user in the database. method creates a user in the database.
	 * returns the user ID to check or he created
	 * 
	 * @param user (String uuserName, String firstName, String lastName)
	 * @return id
	 */
	public Integer create(User user) {
		userRepository.save(user);
		return user.getId();
	}

	/**
	 * This method returns a list of users from the database. if we have a lot of
	 * data in the table, we can use pagination. pagination method is presented in
	 * comments.
	 * 
	 * @return List<User>
	 */
	/*
	 * public Page<User> getAll(Integer page) { return
	 * userRepository.findAll(PageRequest.of(page, 2)); }
	 */
	public List<UserDto> getAll() {
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = new ArrayList<UserDto>();
		for(User user : users) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setUserName(user.getUserName());
			userDto.setFirstName(user.getFirstName());
			userDto.setLastName(user.getLastName());
			userDtos.add(userDto);
		}
		return userDtos;
	}
}

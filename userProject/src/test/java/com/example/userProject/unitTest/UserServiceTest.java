package com.example.userProject.unitTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.example.userProject.model.User;
import com.example.userProject.repository.UserRepository;
import com.example.userProject.service.UserService;

public class UserServiceTest {
	private UserService userService;

	@Before
	public void setup() throws Exception {
		UserRepository userRepository = mock(UserRepository.class);
		userService = new UserService(userRepository);
	}

	@Test
	public void testCreate() {
		User user = new User();
		user.setUserName("AlexPupkin");
		user.setFirstName("Alex");
		user.setLastName("Pupkin");
		when(userService.create(user)).thenReturn(user.getId());
	}

	@Test
	public void testGetAll() {
		UserRepository userRepository = mock(UserRepository.class);
		User user = new User();
		user.setUserName("AlexPupkin");
		user.setFirstName("Alex");
		user.setLastName("Pupkin");
		User user1 = new User();
		user1.setUserName("VasyaVasya");
		user1.setFirstName("Vasya");
		user1.setLastName("Vasya");
		when(userRepository.findAll()).thenReturn(Arrays.asList(user, user1));
		userService.getAll();
	}
}

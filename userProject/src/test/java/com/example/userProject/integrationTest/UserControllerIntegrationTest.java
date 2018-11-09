package com.example.userProject.integrationTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.userProject.controller.Controller;
import com.example.userProject.model.User;
import com.example.userProject.repository.UserRepository;
import com.example.userProject.service.UserService;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UserControllerIntegrationTest {
	@Autowired
	private Controller controller;

	private MockMvc mockMvc;
	@Autowired
	private UserService userService;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testCreate() throws Exception {
		String jsonPerson = String
				.format("{\"userName\": \"AlexPupkin\",\"firstName\": \"Alex\",\"lastName\": \"Pupkin\"}");
		User user = new User();
		user.setUserName("AlexPupkin");
		user.setFirstName("Alex");
		user.setLastName("Pupkin");
		when(userService.create(user)).thenReturn(user.getId());
		mockMvc.perform(post("/user/create").contentType(MediaType.APPLICATION_JSON).content(jsonPerson))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindAll() throws Exception {
		User user = new User();
		user.setUserName("AlexPupkin");
		user.setFirstName("Alex");
		user.setLastName("Pupkin");
		User user1 = new User();
		user1.setUserName("VasyaVasya");
		user1.setFirstName("Vasya");
		user1.setLastName("Vasya");
		when(userService.getAll()).thenReturn(Arrays.asList(user, user1));

		mockMvc.perform(get("/user/getAll")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].userName", is("AlexPupkin")))
				.andExpect(jsonPath("$[0].firstName", is("Alex")))
				.andExpect(jsonPath("$[0].lastName", is("Pupkin")))
				.andExpect(jsonPath("$[1].userName", is("VasyaVasya")))
				.andExpect(jsonPath("$[1].firstName", is("Vasya")))
				.andExpect(jsonPath("$[1].lastName", is("Vasya")));
	}

	@Configuration
	static class Context {

		@Bean
		public Controller getTestService() {
			return new Controller();
		}

		@Bean
		public UserService getMockPersonService() {
			UserService mockPersonService = mock(UserService.class);
			return mockPersonService;
		}

		@Bean
		public UserRepository getMockPersonRepository() {
			UserRepository mockPersonRepository = mock(UserRepository.class);
			return mockPersonRepository;
		}
	}
}

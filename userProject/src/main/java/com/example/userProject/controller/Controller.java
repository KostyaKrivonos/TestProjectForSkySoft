package com.example.userProject.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.userProject.model.User;
import com.example.userProject.service.UserService;

@RestController
@ComponentScan
@RequestMapping(value = "/user")
public class Controller {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> create(@RequestBody User user) {
		return ResponseEntity.ok(userService.create(user));
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public List<User> getAll() {
		return userService.getAll();
	}
}

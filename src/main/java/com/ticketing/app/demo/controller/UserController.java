package com.ticketing.app.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.app.demo.request.UserRequest;
import com.ticketing.app.demo.response.UserResponse;
import com.ticketing.app.demo.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@ApiOperation(value = "Get user by uuid", notes = "Return user by uuid ")
	@GetMapping(value = "/{uuid}")
	public UserResponse getAppUsers(@PathVariable("uuid") UUID uuid) {
		return new UserResponse(userService.get(uuid));
	}

	@ApiOperation(value = "Insert user", notes = "Insert user ")
	@PostMapping()
	public void saveAppUsers(@RequestBody UserRequest req) {
		userService.save(req.getAppUsers());
	}

	@ApiOperation(value = "Update user by uuid", notes = "Update user by uuid")
	@PutMapping(value = "/{uuid}")
	public void updateAppUsers(@RequestBody UserRequest req, @PathVariable("uuid") UUID uuid) {
		userService.update(uuid, req.getAppUsers());
	}

	@ApiOperation(value = "Delete user by uuid", notes = "Delete user by uuid")
	@DeleteMapping(value = "/{uuid}")
	public void deleteAppUsers(@PathVariable("uuid") UUID uuid) {
		userService.delete(uuid);
	}
}

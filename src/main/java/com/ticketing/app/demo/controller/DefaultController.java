package com.ticketing.app.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {
	@GetMapping(value = "/")
	public String getDashboard() {
		return "Hoşgeldiniz";
	}
}

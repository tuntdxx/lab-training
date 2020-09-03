package com.lab.vn.labtraining.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping(value = { "/", "/index" })
	public String homepage() {
		return "index"; // Trả về home.html
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello"; // Trả về hello.html
	}
}

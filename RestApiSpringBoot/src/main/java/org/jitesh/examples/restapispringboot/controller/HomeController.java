package org.jitesh.examples.restapispringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping(value="/ping")
	public String checkStatus() {
		System.out.println("Ping success");
		return "SUCCESS";
	}
}

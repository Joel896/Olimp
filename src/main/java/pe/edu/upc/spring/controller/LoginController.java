package pe.edu.upc.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
	@GetMapping("/login")
	public String login() { return "login"; }
	
	@GetMapping("/")
	public String irLogin() { return "redirect:/login"; }
	
}

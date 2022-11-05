package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class CoregemstoneApplication {

	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CoregemstoneApplication.class, args);
	}

}

package com;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class CoregemstoneApplication {

	@GetMapping("/robots.txt")
	@ResponseBody
	public void robotsBlock(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write("User-agent: *\nAllow: /\n");
		} catch (IOException e) {
			
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CoregemstoneApplication.class, args);
	}

}

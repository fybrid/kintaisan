package com.kyosaka.kintaisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@Controller
public class KintaisanApplication {

	public static void main(String[] args) {
		SpringApplication.run(KintaisanApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "index";
  }

	@GetMapping("/login")
  public String showLoginPage() {
    return "login";
  }
}

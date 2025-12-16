package com.kyosaka.kintaisan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

	@GetMapping("/tologin")
  public String showLoginPage() {
    return "login";
  }
}

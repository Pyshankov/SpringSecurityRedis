package com.example;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			User u1 = new User("Pavel","123321");
			u1.setActivated(true);
			u1.setRole(User.Role.ADMIN);
			User u2 = new User("Vova","123");
			u2.setActivated(true);
			User u3 = new User("Alex","123");

			u1=repository.save(u1);
			u2=repository.save(u2);
			u3=repository.save(u3);

			System.out.println(u1);
			System.out.println(u2);
			System.out.println(u3);
		};
	}

	@RequestMapping(value="api/user/current",produces = "application/json")
	public Map<String,String> helloUser(Principal principal) {
		HashMap<String,String> result = new HashMap<String,String>();
		result.put("username", principal.getName());
		return result;
	}

	@RequestMapping(value="api/logout",method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public String logout(Principal principal) {
		SecurityContextHolder.clearContext();
		return "Success";
	}

}

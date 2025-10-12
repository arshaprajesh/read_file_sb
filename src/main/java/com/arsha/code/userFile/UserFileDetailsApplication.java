package com.arsha.code.userFile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.arsha.code.userFile")
public class UserFileDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserFileDetailsApplication.class, args);
	}

}

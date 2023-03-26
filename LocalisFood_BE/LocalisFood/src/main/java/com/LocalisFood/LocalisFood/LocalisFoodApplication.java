package com.LocalisFood.LocalisFood;

import com.LocalisFood.LocalisFood.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@RequiredArgsConstructor
public class LocalisFoodApplication {
	final AuthService userService;
	public static void main(String[] args) {
		SpringApplication.run(LocalisFoodApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}

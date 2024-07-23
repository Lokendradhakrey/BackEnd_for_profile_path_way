package com.lokendra.BackEnd_for_profile_path_way;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackEndForProfilePathWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndForProfilePathWayApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}

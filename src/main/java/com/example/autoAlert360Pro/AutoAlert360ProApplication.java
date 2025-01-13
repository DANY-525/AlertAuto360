package com.example.autoAlert360Pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Habilita la programación de tareas
public class AutoAlert360ProApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoAlert360ProApplication.class, args);
	}

}

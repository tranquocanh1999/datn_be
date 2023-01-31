package com.main;

import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
@Transactional
public class DatnBeApplication {
	public static void main(String[] args) {
		SpringApplication.run(DatnBeApplication.class, args);
	}
}

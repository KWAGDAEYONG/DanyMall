package com.example.demo;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class DanymallApplication{
	private static final String PROPERTIES = "spring.config.location=classpath:/google.properties";


	public static void main(String[] args) {
		new SpringApplicationBuilder(DanymallApplication.class)
				.properties(PROPERTIES)
				.run(args);
	}
}

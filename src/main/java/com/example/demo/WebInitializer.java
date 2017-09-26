package com.example.demo;

import javafx.application.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {
    private static final String PROPERTIES = "spring.config.location=classpath:/google.properties";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DanymallApplication.class).properties(PROPERTIES);
    }

}

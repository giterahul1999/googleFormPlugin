package com.googleFormPlugin.googleFormPlugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GoogleFormPluginApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleFormPluginApplication.class, args);
	}

}

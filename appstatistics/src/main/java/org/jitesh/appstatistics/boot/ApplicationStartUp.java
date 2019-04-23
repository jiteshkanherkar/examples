package org.jitesh.appstatistics.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.jitesh.appstatistics")
public class ApplicationStartUp {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStartUp.class, args);
	}
}

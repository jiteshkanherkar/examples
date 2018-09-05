package org.jitesh.examples.restapispringboot.boot;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = { "org.jitesh.examples.restapispringboot" })
@EnableAutoConfiguration
public class BootApplication extends WebMvcConfigurerAdapter {

	@Autowired
	ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BootApplication.class);
		application.addListeners(new ApplicationPidFileWriter(new File("/home/jitesh/pid.file")));
		application.run(args);
	}

	@Bean
	public ApplicationWatcher getApplicationWatcher() {
		return new ApplicationWatcher("/home/jitesh/pid.file", applicationContext);
	}
	/*
	 * @Override public void addInterceptors(InterceptorRegistry registry) {
	 * registry.addInterceptor(requestInterceptor); }
	 */
}

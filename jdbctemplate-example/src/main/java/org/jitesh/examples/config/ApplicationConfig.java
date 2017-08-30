package org.jitesh.examples.config;

import org.jitesh.examples.JdbcUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.jitesh.examples")
public class ApplicationConfig {

	@Bean(name="jdbcUtils")
	public JdbcUtils getJdbcUtils() {
		return new JdbcUtils();
	}
}

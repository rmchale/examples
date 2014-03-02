package org.mchaler.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(value="org.mchaler")
@ImportResource("spring-context.xml")

public class App {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}

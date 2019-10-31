package tech.mineapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import tech.mineapp.config.ApiPropertiesConfig;
import tech.mineapp.config.AppPropertiesConfig;

@SpringBootApplication
@EnableConfigurationProperties({ApiPropertiesConfig.class, AppPropertiesConfig.class})
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}

package tech.mineapp;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;

import tech.mineapp.config.AppPropertiesConfig;
import tech.mineapp.config.StorageProperties;
//import tech.mineapp.service.FileUploadService;

@SpringBootApplication
@EnableConfigurationProperties({AppPropertiesConfig.class, StorageProperties.class})
public class BackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner init(FileUploadService fileUploadService) {
//		return (args) -> {
//			fileUploadService.init();
//		};
//	}
}

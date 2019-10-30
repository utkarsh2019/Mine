package tech.mineapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
@ConfigurationProperties("storage")
public class StorageProperties {
	
	private String location = "pictures";
}

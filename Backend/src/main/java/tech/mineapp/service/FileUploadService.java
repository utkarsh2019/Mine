package tech.mineapp.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import tech.mineapp.config.ApiPropertiesConfig;

/**
 * @author utkarsh
 *
 */
@Service
public class FileUploadService {
	
	private ApiPropertiesConfig apiProperties;
	
	private Cloudinary cloudinary;
	
	@Autowired
    public FileUploadService(ApiPropertiesConfig apiProperties) {
        this.apiProperties = apiProperties;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
    			"cloud_name", this.apiProperties.getCloudinary().getCloudName(),
    			"api_key", this.apiProperties.getCloudinary().getApiKey(),
    			"api_secret", this.apiProperties.getCloudinary().getApiSecret()));

    }
	
	@SuppressWarnings("rawtypes")
	public String uploadPhoto(MultipartFile file) throws IOException {
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "image"));
		return (String) uploadResult.get("url");
	}
}

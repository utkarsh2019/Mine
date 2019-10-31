package tech.mineapp.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 * @author utkarsh
 *
 */
@Service
public class FileUploadService {
	
	private static final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
			"cloud_name", "mineapp",
			"api_key", "934122198185593",
			"api_secret", "t-cXLtSFEPqROk8IechyX8nGCQw"));
	
	@SuppressWarnings("rawtypes")
	public String uploadPhoto(MultipartFile file) throws IOException {
		Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "image"));
		return (String) uploadResult.get("url");
	}
}

package tech.mineapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tech.mineapp.config.StorageProperties;
import tech.mineapp.exception.StorageException;
import tech.mineapp.exception.StorageFileNotFoundException;

/**
 * @author utkarsh
 *
 */
@Service
public class FileUploadService {
	
	private final Path rootLocation;
	
	@Autowired
	public FileUploadService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}
	
	public void store(MultipartFile file, String filename) {
		
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file");
			}
//			if (filename.contains("..")) {
//				throw new StorageException("Cannot store file with relative path outside current directory "+filename);
//			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, load(filename), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file "+filename, e);
		}
	}
	
	public void delete(String filename) {
		try {
			Path file = load(filename);
			if (Files.notExists(file)) {
				throw new StorageFileNotFoundException("Could not find file.");
			} else {
				Files.delete(file);
			}
		} catch (IOException e) {
			throw new StorageFileNotFoundException("Could not read file.", e);
		}
	}
	
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}
	
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not find file.");
			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file.", e);
		}
	}
	
	public void init() {
		try {
			if (Files.notExists(rootLocation)) {
				Files.createDirectories(rootLocation);
			}
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}

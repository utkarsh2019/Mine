package tech.mineapp.model.service;

import java.net.URL;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the Service Layer representation of a user
 * 
 * @author amolmoses
 */
@Data
@NoArgsConstructor
public class UserDTO {
	private String userId;
	private String emailId;
	private String firstName;
	private String lastName;
	private String password;
	private List<String> categoryPreferences;
	private int numberOfPreviousSearches;
	private String profilePicture;
	private Boolean isVerified = false;
}

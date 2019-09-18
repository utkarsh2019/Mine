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
	private String UserID;
	private String emailID;
	private String firstName;
	private String lastName;
	private List<String> categoryPreferences;
	private int numberOfPreviousSearches;
	private URL profilePicture;
}

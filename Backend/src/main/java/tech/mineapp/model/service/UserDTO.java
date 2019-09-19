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
	private long UserId;
	private String emailId;
	private String firstName;
	private String lastName;
	private List<String> categoryPreferences;
	private int numberOfPreviousSearches;
	private URL profilePicture;
	public long getUserId() {
		return UserId;
	}
	public void setUserId(long userId) {
		UserId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getCategoryPreferences() {
		return categoryPreferences;
	}
	public void setCategoryPreferences(List<String> categoryPreferences) {
		this.categoryPreferences = categoryPreferences;
	}
	public int getNumberOfPreviousSearches() {
		return numberOfPreviousSearches;
	}
	public void setNumberOfPreviousSearches(int numberOfPreviousSearches) {
		this.numberOfPreviousSearches = numberOfPreviousSearches;
	}
	public URL getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(URL profilePicture) {
		this.profilePicture = profilePicture;
	}
}

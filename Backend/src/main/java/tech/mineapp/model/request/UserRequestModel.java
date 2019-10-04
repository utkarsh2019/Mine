package tech.mineapp.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import tech.mineapp.constants.AuthProvider;

/**
 * @author utkarsh
 *
 */
@Data
public class UserRequestModel {
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String name;
	
	private String profilePicUrl;

	private String categoryPreferences;
	
	private int noOfPreviousSearches;

}

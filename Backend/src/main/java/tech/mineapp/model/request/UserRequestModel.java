package tech.mineapp.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

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

	private String categoryPreferences;
	
	private int noOfSearches;

}

package tech.mineapp.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * Represents an input request for a user
 * 
 * @author amolmoses, utkarsh
 */
@Data
public class UserRequestModel {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String name;
	@NotBlank
	private String password;
}

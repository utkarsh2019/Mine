package tech.mineapp.model.request;

import lombok.Data;

/**
 * Represents an input request for a user
 * 
 * @author amolmoses
 */
@Data
public class UserRequestModel {
	private String emailId;
	private String firstName;
	private String lastName;
	private String password;
}

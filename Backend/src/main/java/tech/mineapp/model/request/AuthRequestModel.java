/**
 * 
 */
package tech.mineapp.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * Request model for Auth Requests
 *
 * @author utkarsh
 */
@Data
public class AuthRequestModel {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;
}

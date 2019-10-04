/**
 * 
 */
package tech.mineapp.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
public class ForgotPasswordRequestModel {
	@NotBlank
	@Email
	private String email;
}

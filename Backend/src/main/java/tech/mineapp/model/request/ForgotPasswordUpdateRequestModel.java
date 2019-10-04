/**
 * 
 */
package tech.mineapp.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
public class ForgotPasswordUpdateRequestModel {
	@NotBlank
	private String password;
}

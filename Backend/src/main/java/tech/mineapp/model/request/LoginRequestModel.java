/**
 * 
 */
package tech.mineapp.model.request;

import lombok.Data;

/**
 * Request model for Login Requests
 *
 * @author utkarsh
 */
@Data
public class LoginRequestModel {
	private String emailId;
	private String password;
}

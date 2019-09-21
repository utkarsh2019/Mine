/**
 * 
 */
package tech.mineapp.model.request;

import lombok.Data;

/**
 * @author utkarsh
 *
 */
@Data
public class LoginRequestModel {
	private String emailId;
	private String password;
}

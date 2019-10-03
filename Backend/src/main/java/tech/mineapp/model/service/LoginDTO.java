package tech.mineapp.model.service;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author utkarsh
 *
 */
@Data
@NoArgsConstructor
public class LoginDTO {
	private String emailId;
	private String password;
}

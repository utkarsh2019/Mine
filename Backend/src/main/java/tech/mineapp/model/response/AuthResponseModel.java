package tech.mineapp.model.response;

import lombok.Data;

/**
 * @author utkarsh
 *
 */

@Data
public class AuthResponseModel implements ResponseModel {
	private String accessToken;
	private String tokenType = "Bearer";
	
	public AuthResponseModel(String accessToken) {
		this.accessToken = accessToken;
	}
}

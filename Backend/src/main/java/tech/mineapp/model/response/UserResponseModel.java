package tech.mineapp.model.response;

import lombok.Data;
import tech.mineapp.constants.AuthProvider;

/**
 * Response Model representing User Details
 *
 * @author amolmoses, utkarsh
 */
@Data
public class UserResponseModel implements ResponseModel {
	private String email;
	private String name;
	private String categoryPreferences;
	private int noOfPreviousSearches;
	private String profilePicURL;
	private AuthProvider provider;
}

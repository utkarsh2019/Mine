package tech.mineapp.model.response;

import lombok.Data;

/**
 * Response Model representing User Details
 *
 * @author amolmoses, utkarsh
 */
@Data
public class UserResponseModel implements ResponseModel {
	private String email;
	private String name;
	private Boolean isVerified;
	private String categoryPreferences;
	private int noOfPreviousSearches;
	private String profilePicURL;
}

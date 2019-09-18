package tech.mineapp.model.response;



import java.net.URL;
import java.util.List;

import lombok.Data;

@Data
public class UserResponseModel implements ResponseModel {
	private long UserId;
	private String emailId;
	private String firstName;
	private String lastName;
	private List<String> categoryPreferences;
	private int numberOfPreviousSearches;
	private URL profilePicture;
}

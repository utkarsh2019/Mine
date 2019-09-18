package tech.mineapp.model.response;



import java.net.URL;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserResponseModel extends GenericResponseModel {
	private String UserID;
	private String emailID;
	private String firstName;
	private String lastName;
	private List<String> categoryPreferences;
	private int numberOfPreviousSearches;
	private URL profilePicture;
}

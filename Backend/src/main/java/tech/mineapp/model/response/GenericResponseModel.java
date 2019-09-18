package tech.mineapp.model.response;

import lombok.Data;

/**
 * Represent a Generic Class (should be subclassed)
 * with fields required in every response
 * 
 * @author amolmoses
 */
@Data
public class GenericResponseModel {
	private String verb;
	private String endpoint;
	private String status;
	private String errorMessage;
}

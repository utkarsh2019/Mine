package tech.mineapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tech.mineapp.model.response.ContainerResponseModel;

/**
 * @author utkarsh
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ContainerResponseModel> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ContainerResponseModel response = new ContainerResponseModel();
    	
    	response.setVerb("POST");
    	response.setEndpoint(request.getDescription(false).substring(4));
    	response.setStatus("FAIL");
		response.setErrorMessage(ex.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
	
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	ContainerResponseModel response = new ContainerResponseModel();
    	response.setVerb("POST");
    	response.setEndpoint(request.getDescription(false).substring(4));
    	response.setStatus("FAIL");
    	if (ex.getLocalizedMessage().contains("must be a well-formed email address")) {
    		response.setErrorMessage("Must be a well-formed email address");
    	}
    	else {
    		response.setErrorMessage("Invalid request");
    	}
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
}

package tech.mineapp.exception;

/**
 * @author utkarsh
 *
 */
@SuppressWarnings("serial")
public class StorageException extends RuntimeException{

	public StorageException(String message) {
		super(message);
	}
	
	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}

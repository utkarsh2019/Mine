package tech.mineapp.exception;

/**
 * @author utkarsh
 *
 */
@SuppressWarnings("serial")
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super("User does not exist!");
    }
}

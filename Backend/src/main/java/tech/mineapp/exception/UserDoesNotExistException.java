package tech.mineapp.exception;

/**
 * @author utkarsh
 *
 */
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super("User does not exist!");
    }
}

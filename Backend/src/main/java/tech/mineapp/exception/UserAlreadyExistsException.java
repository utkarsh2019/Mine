package tech.mineapp.exception;

/**
 * Exception to be thrown when a new user is tried to be persisted
 * into the DB, when a user with the same email already exists
 *
 * @author amolmoses
 */
@SuppressWarnings("serial")
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("User already exists!");
    }
}

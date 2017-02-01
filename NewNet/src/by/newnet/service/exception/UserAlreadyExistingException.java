package by.newnet.service.exception;

/**
 * The Class UserAlreadyExistingException.
 */
public class UserAlreadyExistingException extends ServiceException{

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new user already existing exception.
	 */
	public UserAlreadyExistingException() {
	}

	/**
	 * Instantiates a new user already existing exception.
	 *
	 * @param message the message
	 */
	public UserAlreadyExistingException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new user already existing exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public UserAlreadyExistingException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new user already existing exception.
	 *
	 * @param e the e
	 */
	public UserAlreadyExistingException(Exception e) {
		super(e);

	}
}

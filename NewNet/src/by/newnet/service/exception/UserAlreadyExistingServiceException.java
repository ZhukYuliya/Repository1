package by.newnet.service.exception;

/**
 * The Class UserAlreadyExistingException.
 */
public class UserAlreadyExistingServiceException extends ServiceException{

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new user already existing exception.
	 */
	public UserAlreadyExistingServiceException() {
	}

	/**
	 * Instantiates a new user already existing exception.
	 *
	 * @param message the message
	 */
	public UserAlreadyExistingServiceException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new user already existing exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public UserAlreadyExistingServiceException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new user already existing exception.
	 *
	 * @param e the e
	 */
	public UserAlreadyExistingServiceException(Exception e) {
		super(e);

	}
}

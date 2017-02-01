package by.newnet.service.exception;

/**
 * The Class ServiceAuthorizationException.
 */
public class ServiceAuthorizationException extends ServiceException{

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new service authorization exception.
	 */
	public ServiceAuthorizationException() {
	}

	/**
	 * Instantiates a new service authorization exception.
	 *
	 * @param message the message
	 */
	public ServiceAuthorizationException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new service authorization exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public ServiceAuthorizationException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new service authorization exception.
	 *
	 * @param e the e
	 */
	public ServiceAuthorizationException(Exception e) {
		super(e);

	}
}

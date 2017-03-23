package service.exception;

/**
 * The Class ServiceAuthorizationException.
 */
public class ServiceAuthenticationException extends ServiceException{

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new service authorization exception.
	 */
	public ServiceAuthenticationException() {
	}

	/**
	 * Instantiates a new service authorization exception.
	 *
	 * @param message the message
	 */
	public ServiceAuthenticationException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new service authorization exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public ServiceAuthenticationException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new service authorization exception.
	 *
	 * @param e the e
	 */
	public ServiceAuthenticationException(Exception e) {
		super(e);

	}
}

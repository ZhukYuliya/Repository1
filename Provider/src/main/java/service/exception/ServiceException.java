package service.exception;

/**
 * The Class ServiceException.
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new service exception.
	 */
	public ServiceException() {
	}

	/**
	 * Instantiates a new service exception.
	 *
	 * @param message the message
	 */
	public ServiceException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new service exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public ServiceException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new service exception.
	 *
	 * @param e the e
	 */
	public ServiceException(Exception e) {
		super(e);

	}
}
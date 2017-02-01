package by.newnet.service.exception;

/**
 * The Class UserAlreadyExistingException.
 */
public class FailedPaymentServiceException extends ServiceException{

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new user already existing exception.
	 */
	public FailedPaymentServiceException() {
	}

	/**
	 * Instantiates a new user already existing exception.
	 *
	 * @param message the message
	 */
	public FailedPaymentServiceException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new user already existing exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public FailedPaymentServiceException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new user already existing exception.
	 *
	 * @param e the e
	 */
	public FailedPaymentServiceException(Exception e) {
		super(e);

	}
}

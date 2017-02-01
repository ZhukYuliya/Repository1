package by.newnet.service.exception;

/**
 * The Class DuplicateTariffServiceException.
 */
public class DuplicateTariffServiceException extends Exception {

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new duplicate tariff service exception.
	 */
	public DuplicateTariffServiceException() {
	}

	/**
	 * Instantiates a new duplicate tariff service exception.
	 *
	 * @param message the message
	 */
	public DuplicateTariffServiceException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new duplicate tariff service exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public DuplicateTariffServiceException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new duplicate tariff service exception.
	 *
	 * @param e the e
	 */
	public DuplicateTariffServiceException(Exception e) {
		super(e);

	}
}
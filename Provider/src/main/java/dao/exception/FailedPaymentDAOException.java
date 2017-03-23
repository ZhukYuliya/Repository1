package dao.exception;

/**
 * The Class DAOException.
 */
public class FailedPaymentDAOException extends Exception{

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new DAO exception.
	 */
	public FailedPaymentDAOException() {
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param message the message
	 */
	public FailedPaymentDAOException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public FailedPaymentDAOException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param e the e
	 */
	public FailedPaymentDAOException(Exception e) {
		super(e);

	}
}
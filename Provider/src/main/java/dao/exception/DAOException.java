package dao.exception;

/**
 * The Class DAOException.
 */
public class DAOException extends Exception{

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new DAO exception.
	 */
	public DAOException() {
	}

	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param message the message
	 */
	public DAOException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public DAOException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new DAO exception.
	 *
	 * @param e the e
	 */
	public DAOException(Exception e) {
		super(e);

	}
}
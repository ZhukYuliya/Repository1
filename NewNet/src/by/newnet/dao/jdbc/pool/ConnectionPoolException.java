package by.newnet.dao.jdbc.pool;

import by.newnet.dao.exception.DAOException;

/**
 * The Class ConnectionPoolException.
 */
public class ConnectionPoolException extends DAOException {

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new connection pool exception.
	 */
	public ConnectionPoolException() {
	}

	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message the message
	 */
	public ConnectionPoolException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public ConnectionPoolException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new connection pool exception.
	 *
	 * @param e the e
	 */
	public ConnectionPoolException(Exception e) {
		super(e);

	}
}

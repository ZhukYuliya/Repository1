package by.newnet.dao.jdbc.pool;

import by.newnet.dao.exception.DAOException;

public class ConnectionPoolException extends DAOException {

private static final long serialVersionUID = 1L;
	
	public ConnectionPoolException() {
	}

	public ConnectionPoolException(String message) {
		super(message);

	}
	public ConnectionPoolException(String message, Exception e) {
		super(message, e);

	}
	
	public ConnectionPoolException(Exception e) {
		super(e);

	}
}

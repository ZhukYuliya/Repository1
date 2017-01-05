package by.newnet.dao.jdbc.pool;

@SuppressWarnings("serial")
public class ConnectionPoolException extends Exception {
	public ConnectionPoolException() {}
	public ConnectionPoolException(Exception e) {
		super(e);

	}
}

package by.newnet.dao.jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
	private static final ConnectionPool instance = new ConnectionPool();
	
	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;
	
	public static ConnectionPool getInstance(){
		return instance;
	}

	private ConnectionPool() {
		DBResourceManager dbResourseManager = DBResourceManager.getInstance();
		this.driverName = dbResourseManager.getValue(DBParameter.DB_DRIVER);
		this.url = dbResourseManager.getValue(DBParameter.DB_URL);
		this.user = dbResourseManager.getValue(DBParameter.DB_USER);
		this.password = dbResourseManager.getValue(DBParameter.DB_PASSWORD);

		try {
			this.poolSize = Integer.parseInt(dbResourseManager.getValue(DBParameter.DB_POOL_SIZE));
		} catch (NumberFormatException e) {
			poolSize = 100;
		}
	}

	public void initPoolData() throws ConnectionPoolException {

		try {
			Class.forName(driverName);
			givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);

			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				connectionQueue.add(connection);
			}

		} catch (SQLException e) {
			throw new ConnectionPoolException();
		
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException();
			
		}

	}

	public void dispose() {
		clearConnectionQueue();
	}

	private void clearConnectionQueue() {

		closeConnectionsQueue(givenAwayConQueue);
		closeConnectionsQueue(connectionQueue);

	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) {
		for (Connection con : queue) {
			try {
				con.close();
			} catch (SQLException e) {
			
			}
		}
	}

	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			
			 throw new ConnectionPoolException();
		}
		return connection;
	}

	public void releaseConnection(Connection connection) throws ConnectionPoolException {
		if (connection == null) {
			throw new ConnectionPoolException();
		}

		try {
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new ConnectionPoolException();
		}

		givenAwayConQueue.remove(connection);
		connectionQueue.add(connection);

	}

}

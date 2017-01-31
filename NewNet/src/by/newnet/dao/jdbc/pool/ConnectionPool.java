package by.newnet.dao.jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class ConnectionPool {
	
	private static final Logger logger = Logger.getLogger(ConnectionPool.class);
// log only exc or info too?
	private static final ConnectionPool instance = new ConnectionPool();

	private BlockingQueue<Connection> connectionQueue;
	private BlockingQueue<Connection> givenAwayConQueue;

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	public static ConnectionPool getInstance() {
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
			poolSize = 5;
		}
		//to prevent null pointer exc
		givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
		connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
	}

	public void initPoolData() throws ConnectionPoolException {
		logger.debug("Trying to initialized connection pool");
		try {
			Class.forName(driverName);
			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				connectionQueue.add(connection);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new ConnectionPoolException(e);
		}
		logger.debug("Connection pool is initialized");
	}
//logged in listener
	public void dispose() throws ConnectionPoolException {
		clearConnectionQueue();
	}

	private void clearConnectionQueue() throws ConnectionPoolException {

		closeConnectionsQueue(givenAwayConQueue);
		closeConnectionsQueue(connectionQueue);

	}

	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws ConnectionPoolException {
		for (Connection con : queue) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new ConnectionPoolException(e);
			}
		}
	}

	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection;
		try {
			connection = connectionQueue.take();
			givenAwayConQueue.add(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException(e);
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
			throw new ConnectionPoolException(e);
		}

		givenAwayConQueue.remove(connection);
		connectionQueue.add(connection);

	}

}

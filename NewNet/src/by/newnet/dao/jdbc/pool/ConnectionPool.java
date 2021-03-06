package dao.jdbc.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

/**
 * The Class ConnectionPool. 
 */
public class ConnectionPool {
	
	private static final Logger logger = Logger.getLogger(ConnectionPool.class);

	private static final ConnectionPool instance = new ConnectionPool();

	private final BlockingQueue<Connection> connectionQueue;
	private final BlockingQueue<Connection> givenAwayConQueue;

	private final String driverName;
	private final String url;
	private final String user;
	private final String password;
	private int poolSize;

	/**
	 * Gets the single instance of ConnectionPool.
	 *
	 * @return single instance of ConnectionPool
	 */
	public static ConnectionPool getInstance() {
		return instance;
	}

	/**
	 * Instantiates a new connection pool.
	 */
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
		givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
		connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
	}

	/**
	 * Inits the pool data.
	 *
	 * @throws ConnectionPoolException the connection pool exception
	 */
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

/**
 * Dispose.
 *
 * @throws ConnectionPoolException the connection pool exception
 */
	public void dispose() throws ConnectionPoolException {
		clearConnectionQueue();
	}

	/**
	 * Clear connection queue.
	 *
	 * @throws ConnectionPoolException the connection pool exception
	 */
	private void clearConnectionQueue() throws ConnectionPoolException {

		closeConnectionsQueue(givenAwayConQueue);
		closeConnectionsQueue(connectionQueue);

	}

	/**
	 * Close connections queue.
	 *
	 * @param queue the queue
	 * @throws ConnectionPoolException the connection pool exception
	 */
	private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws ConnectionPoolException {
		for (Connection con : queue) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new ConnectionPoolException(e);
			}
		}
	}

	/**
	 * Take connection.
	 *
	 * @return the connection
	 * @throws ConnectionPoolException the connection pool exception
	 */
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

	/**
	 * Release connection.
	 *
	 * @param connection the connection
	 * @throws ConnectionPoolException the connection pool exception
	 */
	public void releaseConnection(Connection connection) throws ConnectionPoolException {
		if (connection == null) {
			throw new ConnectionPoolException("Empty connection is passed to release connection method"
					+ Thread.getAllStackTraces().toString());
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

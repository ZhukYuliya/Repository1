package dao.jdbc.pool;

import java.util.ResourceBundle;

/**
 * The Class DBResourceManager.
 */
public class DBResourceManager {
	private static final DBResourceManager instance = new DBResourceManager();

	private final ResourceBundle bundle;

	/**
	 * Instantiates a new DB resource manager.
	 */
	private DBResourceManager() {
		bundle = ResourceBundle.getBundle("db");
	}

	/**
	 * Gets the value.
	 *
	 * @param key
	 *            the key
	 * @return the value
	 */
	public String getValue(String key) {
		return bundle.getString(key);
	}

	/**
	 * Gets the single instance of DBResourceManager.
	 *
	 * @return single instance of DBResourceManager
	 */
	public static DBResourceManager getInstance() {
		return instance;
	}
}

package by.newnet.dao.jdbc.pool;

import java.util.ResourceBundle;

public class DBResourceManager {
	private static final DBResourceManager instance = new DBResourceManager();
	
	private final ResourceBundle bundle;

	private DBResourceManager() {
		bundle = ResourceBundle.getBundle("resources.db");
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
	
	public static DBResourceManager getInstance(){
		return instance;
	}
}

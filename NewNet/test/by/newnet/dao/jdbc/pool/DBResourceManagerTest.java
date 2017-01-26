package by.newnet.dao.jdbc.pool;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Test;

public class DBResourceManagerTest {
	public static final String TEST_POOL_SIZE = "db.pool.size";
	public static final String TEST_RESOURCES = "resources.database";

	@Test
	public void getValue() {
		String expected = "5";
		String actual = DBResourceManager.getInstance().getValue(TEST_POOL_SIZE);;
		Assert.assertEquals(expected, actual);
	}
	@Test (expected = MissingResourceException.class)
	public void getBundle() {
		ResourceBundle bundle = ResourceBundle.getBundle(TEST_RESOURCES);
/*
		String expected = "5";
		String actual = DBResourceManager.getInstance().getValue(TEST_POOL_SIZE);;
		Assert.assertEquals(expected, actual);*/
	}
}

package dao.jdbc.pool;

import org.junit.Assert;
import org.junit.Test;

public class DBResourceManagerTest {
	public static final String TEST_POOL_SIZE = "db.pool.size";
	public static final String TEST_RESOURCES = "resources.database";

	@Test
	public void getValue() {
		String expected = "5";
		String actual = DBResourceManager.getInstance().getValue(TEST_POOL_SIZE);
		Assert.assertEquals(expected, actual);
	}
}

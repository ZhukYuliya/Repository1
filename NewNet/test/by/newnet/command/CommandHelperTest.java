package by.newnet.command;

import org.junit.Assert;
import org.junit.Test;

import by.newnet.command.impl.Registration;

public class CommandHelperTest {
	public static final String TEST_COMMAND = "registration";
	public static final String WRONG_TEST_COMMAND = "register";

	@Test
	public void getCommand() {
		CommandHelper commandHelper = new CommandHelper();
		Command expected = new Registration();
		Command actual = commandHelper.getCommand(TEST_COMMAND);
		Assert.assertEquals(expected, actual);
	}
	@Test (expected = Exception.class)
	public void getNonexistingCommand()  {
		CommandHelper commandHelper = new CommandHelper();
		Command expected = new Registration();
		Command actual = commandHelper.getCommand(WRONG_TEST_COMMAND);
		Assert.assertEquals(expected, actual);
	}
}

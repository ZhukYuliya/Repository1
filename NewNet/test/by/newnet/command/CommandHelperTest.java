package by.newnet.command;

import org.junit.Assert;
import org.junit.Test;

import by.newnet.command.exception.IllegalCommandException;
import by.newnet.command.impl.RegistrationCommand;

public class CommandHelperTest {
	public static final String TEST_COMMAND = "registration";
	public static final String WRONG_TEST_COMMAND = "register";

	@Test
	public void getCommand() throws IllegalCommandException {
		CommandHelper commandHelper = new CommandHelper();
		Object expected = RegistrationCommand.class;
		Object actual = commandHelper.getCommand(TEST_COMMAND).getClass();
		Assert.assertEquals(expected, actual);
	}
	@Test (expected = IllegalCommandException.class)
	public void getNonexistingCommand() throws IllegalCommandException  {
		CommandHelper commandHelper = new CommandHelper();
		commandHelper.getCommand(WRONG_TEST_COMMAND);
	}
}

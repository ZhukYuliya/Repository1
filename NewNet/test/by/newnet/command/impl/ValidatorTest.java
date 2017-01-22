package by.newnet.command.impl;

import org.junit.Assert;
import org.junit.Test;


public class ValidatorTest {
	public static final String TEST_PASSWORD = "qwe1!A";
	public static final String TEST_EMAIL = "maria!1@gmail.com";
	public static final String TEST_BYN_AMOUNT = "12";
	public static final String TEST_CONTRACT = "0123748267000";

	public static final String INVALID_EMAIL = "invalid_email";
	public static final String INVALID_BYN_AMOUNT = "invalid_byn_amount";
	public static final String INVALID_CONTRACT = "invalid_contract_number";
	
	@Test
	public void validatePassword() {
		String expected = null;
		String actual = Validator.validatePassword(TEST_PASSWORD);;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void validateEmail() {
		String expected = INVALID_EMAIL;
		String actual = Validator.validateEmail(TEST_EMAIL);
		Assert.assertEquals(expected, actual);

	}
	@Test
	public void validateBynAmount() {
		String expected = INVALID_BYN_AMOUNT;
		String actual = Validator.validateBynAmount(TEST_BYN_AMOUNT);
		Assert.assertEquals(expected, actual);

	}
	@Test
	public void validateContract() {
		String expected = INVALID_CONTRACT;
		String actual = Validator.validateContract(TEST_CONTRACT);
		Assert.assertEquals(expected, actual);

	}
}

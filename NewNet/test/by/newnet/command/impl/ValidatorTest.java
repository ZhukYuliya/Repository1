package by.newnet.command.impl;

import org.junit.Assert;
import org.junit.Test;

import by.newnet.command.validator.Validator;


public class ValidatorTest {
	public static final String TEST_PASSWORD = "qwe1!A";
	public static final String TEST_EMAIL = "maria!1@gmail.com";
	public static final String TEST_INCORRECT_BYN_AMOUNT = "12.000";
	public static final String TEST_BYN_AMOUNT = "12";
	public static final String TEST_CONTRACT = "0123748267000";
	public static final String TEST_CARD_NUMBER = "0293849604481728";
	public static final String TEST_EXPIRATION_MONTH = "12";
	public static final String TEST_EXPIRATION_YEAR = "16";
	public static final String TEST_SECURITY_CODE = "123";
	public static final String TEST_FIRST_NAME = "OLEG";
	public static final String TEST_SECOND_NAME = "ZHUKOV";

	public static final String INVALID_EMAIL = "invalid_email";
	public static final String INVALID_BYN_AMOUNT = "invalid_byn_amount";
	public static final String INVALID_CONTRACT = "invalid_contract_number";
	public static final String EXPIRED_CARD = "expired_card";
	
	@Test
	public void validatePassword() {
		String actual = Validator.validatePassword(TEST_PASSWORD);
		Assert.assertNull(actual);
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
		String actual = Validator.validateBynAmount(TEST_INCORRECT_BYN_AMOUNT);
		Assert.assertEquals(expected, actual);

	}
	@Test
	public void validateContract() {
		String expected = INVALID_CONTRACT;
		String actual = Validator.validateContract(TEST_CONTRACT);
		Assert.assertEquals(expected, actual);

	}
	@Test
	public void  validateCardDetails(){
		String expected = EXPIRED_CARD;
		String actual = Validator.validateCardDetails(TEST_CARD_NUMBER, TEST_EXPIRATION_MONTH, 
				TEST_EXPIRATION_YEAR, TEST_SECURITY_CODE,
				TEST_FIRST_NAME, TEST_SECOND_NAME, TEST_BYN_AMOUNT);
		Assert.assertEquals(expected, actual);

}
}
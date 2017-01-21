package by.newnet.command.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Validator {
	/*
	 * private static final String CONTRACT = "contract"; private static final
	 * String NAME = "name"; private static final String TARIFF_NAME =
	 * "tariffName"; private static final String BYN_AMOUNT = "bynAmount";
	 * private static final String PASSWORD = "password"; private static final
	 * String EMAIL = "email"; private static final String PHONE = "phone";
	 */

	// card details validation needed?
	// check all the patterns on regex
	public static final Pattern CONTRACT_PATTERN = Pattern.compile("^[1-9]\\d{11}");
	public static final Pattern NAME_PATTERN = Pattern.compile("^[A-Z][a-z]{1,40}");
	public static final Pattern EMAIL_PATTERN =
	        Pattern.compile("^[A-z0-9]+@[A-z0-9]+\\.[A-z]{2,}$");
	public static final Pattern PHONE_PATTERN = Pattern.compile("\\d{9,13}");
	// todo
	public static final Pattern PASSWORD_PATTERN =
	        Pattern.compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,40}");
	public static final Pattern TARIFF_NAME_PATTERN =
	        Pattern.compile("[A-Z]([a-z//d{Space}{Punct}]){1,40}");
	public static final Pattern BYN_AMOUNT_PATTERN = Pattern.compile("\\d\\.\\d{2}");
	public static final Pattern SPEED_MBPR_PATTERN = Pattern.compile("^[1-9]\\d?");
	public static final Pattern TRAFFIC_PATTERN = Pattern.compile("0|(^[1-9]\\d?)");

	public static String checkEmptyFields(String... parameters) {
		String message = null;
		for (String parameter : parameters) {
			if (StringUtils.isEmpty(parameter)) {
				message = "empty_fields";
				break;
			}
		}
		return message;
	}

	public static String validateContract(String contract) {
		Matcher contractMatcher = CONTRACT_PATTERN.matcher(contract);
		if (!contractMatcher.matches()) {
			return "invalid_contract_number";
		}
		return null;
	}

	public static String validateName(String name) {
		Matcher nameMatcher = NAME_PATTERN.matcher(name);
		if (!nameMatcher.matches()) {
			return "invalid_name";
		}
		return null;
	}

	public static String validatePhone(String phone) {
		Matcher phoneMatcher = PHONE_PATTERN.matcher(phone);
		if (!phoneMatcher.matches()) {
			return "invalid_phone";
		}
		return null;
	}

	public static String validateEmail(String email) {
		Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
		if (!emailMatcher.matches()) {
			return "invalid_email";
		}
		return null;
	}

	public static String validateTariffName(String tariffName) {
		Matcher tariffNameMatcher = TARIFF_NAME_PATTERN.matcher(tariffName);
		if (!tariffNameMatcher.matches()) {
			return "invalid_tariff_name";
		}
		return null;
	}

	public static String validateBynAmount(String bynAmount) {
		Matcher bynMatcher = BYN_AMOUNT_PATTERN.matcher(bynAmount);
		if (!bynMatcher.matches()) {
			return "invalid_byn_amount";
		}
		return null;
	}

	public static String validatePassword(String password) {
		Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
		if (!passwordMatcher.matches()) {
			return "invalid_password";
		}
		return null;
	}

	public static String validateSpeed(String speed) {
		Matcher speedMatcher = SPEED_MBPR_PATTERN.matcher(speed);
		if (!speedMatcher.matches()) {
			return "invalid_speed";
		}
		return null;
	}

	public static String validateTraffic(String traffic) {
		Matcher trafficMatcher = TRAFFIC_PATTERN.matcher(traffic);
		if (!trafficMatcher.matches()) {
			return "invalid_traffic";
		}
		return null;
	}

	public static String compareNewPasswords(String password, String reenterPassword) {
		if (!password.equals(reenterPassword)) {
			return "different_passwords";
		}
		return null;
	}
	public static String compareOldNewPasswords(String oldpassword, String newPassword) {
		if (oldpassword.equals(newPassword)) {
			return "old_new_passwords_must_be_different";
		}
		return null;
	}

	public static String validateRegistration(String password, String reenterPassword, String phone,
	        String email) {
		String message = checkEmptyFields(password, reenterPassword, phone, email);
		if (message == null) {
			message = validatePassword(password);
		}
		if (message == null) {
			message = compareNewPasswords(password, reenterPassword);
		}
		if (message == null) {
			message = validatePhone(phone);
		}
		if (message == null) {
			message = validatePhone(phone);
		}
		if (message == null) {
			message = validateEmail(email);
		}
		return message;
	}

	public static String validateNewContract(String contract, String firstName, String secondName) {
		String message = checkEmptyFields(contract, firstName, secondName);
		if (message == null) {
			message = validatePassword(contract);
		}
		if (message == null) {
			message = validateName(firstName);
		}
		if (message == null) {
			message = validateName(secondName);
		}
		return message;
	}

	public static String validateSaveUser(String account, String firstName, String secondName,
	        String phone, String email) {
		String message = checkEmptyFields(account, firstName, secondName, phone, email);
		if (message == null) {
			message = validateContract(account);
		}
		if (message == null) {
			message = validateName(firstName);
		}
		if (message == null) {
			message = validateName(secondName);
		}
		if (message == null) {
			message = validatePhone(phone);
		}
		if (message == null) {
			message = validateEmail(email);
		}
		return message;
	}

	public static String validateRequest(String firstName, String email, String phone,
	        String address) {
		String message = checkEmptyFields(firstName, email, phone, address);
		if (message == null) {
			message = validateName(firstName);
		}
		if (message == null) {
			message = validateEmail(email);
		}
		if (message == null) {
			message = validatePhone(phone);
		}
		return message;
	}

	public static String validateTariff(String name, String priceParameter, String speedParameter,
	        String trafficParameter) {
		String message = checkEmptyFields(name, priceParameter, speedParameter, trafficParameter);
		if (message == null) {
			message = validateTariffName(name);
		}
		if (message == null) {
			message = validateBynAmount(priceParameter);
		}
		if (message == null) {
			message = validateSpeed(speedParameter);
		}
		if (message == null) {
			message = validateTraffic(trafficParameter);
		}
		return message;
	}

	public static String validateContacts(String phone, String email) {
		String message = checkEmptyFields(phone, email);
		if (message == null) {
			message = validatePhone(phone);
		}
		if (message == null) {
			message = validateEmail(email);
		}
		return message;
	}

	public static String validatePasswordUpdate(String oldPassword, String newPassword,
	        String reenterNewPassword) {
		String message = checkEmptyFields(oldPassword, newPassword, reenterNewPassword);
		if (message == null) {
			message = validatePassword(newPassword);
		}
		if (message == null) {
			message = compareOldNewPasswords(oldPassword, newPassword);
		}
		if (message == null) {
			message = compareNewPasswords(newPassword, reenterNewPassword);
		}
		return message;
	}
}

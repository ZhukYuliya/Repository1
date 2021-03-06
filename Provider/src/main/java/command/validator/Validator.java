package command.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class Validator. Validates all the needed request parameters for commands.
 */
public class Validator {
	
	public static final Pattern CONTRACT_PATTERN = Pattern.compile("[1-9]\\d{11}");
	public static final Pattern NAME_PATTERN = Pattern.compile("[�-ߨ][�-��]{1,40}");
	public static final Pattern CARDHOLDER_NAME_PATTERN = Pattern.compile("[A-z]{1,40}");
	public static final Pattern EMAIL_PATTERN =
	        Pattern.compile("[A-z0-9_\\-.]+@[A-z0-9\\-.]+\\.[A-z]{2,10}");
	public static final Pattern PHONE_PATTERN = Pattern.compile("[1-9]\\d{8,12}");
	// min 6 characters: at least 1 uppercase, 1 lowercase, 1 number and 1 special character
	public static final Pattern PASSWORD_PATTERN = Pattern
	        .compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{6,}");
	public static final Pattern TARIFF_NAME_PATTERN =
	        Pattern.compile("[A-Z�-ߨ]([A-z�-���\\d ]){1,40}");
	public static final Pattern BYN_AMOUNT_PATTERN = Pattern.compile("([1-9]\\d*)|([1-9]\\d*)(\\.\\d{0,2})?");
	public static final Pattern SPEED_MBPR_PATTERN = Pattern.compile("[1-9]\\d?");
	public static final Pattern TRAFFIC_PATTERN = Pattern.compile("0|([1-9]\\d?)");
	public static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("\\d{16}");
	public static final Pattern SECURITY_CODE_PATTERN = Pattern.compile("\\d{3}");
	public static final Pattern MONTH_PATTERN = Pattern.compile("0[1-9]|1[012]");
	public static final Pattern YEAR_PATTERN = Pattern.compile("[1-2][0-9]");

	/**
	 * Check empty fields.
	 *
	 * @param parameters the parameters
	 * @return the string
	 */
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

	/**
	 * Validate contract.
	 *
	 * @param contract the contract
	 * @return the string
	 */
	public static String validateContract(String contract) {
		Matcher contractMatcher = CONTRACT_PATTERN.matcher(contract);
		if (!contractMatcher.matches()) {
			return "invalid_contract_number";
		}
		return null;
	}

	/**
	 * Validate name.
	 *
	 * @param name the name
	 * @return the string
	 */
	public static String validateName(String name) {
		Matcher nameMatcher = NAME_PATTERN.matcher(name);
		if (!nameMatcher.matches()) {
			return "invalid_name";
		}
		return null;
	}

	/**
	 * Validate phone.
	 *
	 * @param phone the phone
	 * @return the string
	 */
	public static String validatePhone(String phone) {
		Matcher phoneMatcher = PHONE_PATTERN.matcher(phone);
		if (!phoneMatcher.matches()) {
			return "invalid_phone";
		}
		return null;
	}

	/**
	 * Validate email.
	 *
	 * @param email the email
	 * @return the string
	 */
	public static String validateEmail(String email) {
		Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
		if (!emailMatcher.matches()) {
			return "invalid_email";
		}
		return null;
	}

	/**
	 * Validate tariff name.
	 *
	 * @param tariffName the tariff name
	 * @return the string
	 */
	public static String validateTariffName(String tariffName) {
		Matcher tariffNameMatcher = TARIFF_NAME_PATTERN.matcher(tariffName);
		if (!tariffNameMatcher.matches()) {
			return "invalid_tariff_name";
		}
		return null;
	}

	/**
	 * Validate byn amount.
	 *
	 * @param bynAmount the byn amount
	 * @return the string
	 */
	public static String validateBynAmount(String bynAmount) {
		Matcher bynMatcher = BYN_AMOUNT_PATTERN.matcher(bynAmount);
		if (!bynMatcher.matches()) {
			return "invalid_byn_amount";
		}
		return null;
	}

	/**
	 * Validate password.
	 *
	 * @param password the password
	 * @return the string
	 */
	public static String validatePassword(String password) {
		Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
		if (!passwordMatcher.matches()) {
			return "invalid_password";
		}
		return null;
	}

	/**
	 * Validate speed.
	 *
	 * @param speed the speed
	 * @return the string
	 */
	public static String validateSpeed(String speed) {
		Matcher speedMatcher = SPEED_MBPR_PATTERN.matcher(speed);
		if (!speedMatcher.matches()) {
			return "invalid_speed";
		}
		return null;
	}

	/**
	 * Validate traffic.
	 *
	 * @param traffic the traffic
	 * @return the string
	 */
	public static String validateTraffic(String traffic) {
		Matcher trafficMatcher = TRAFFIC_PATTERN.matcher(traffic);
		if (!trafficMatcher.matches()) {
			return "invalid_traffic";
		}
		return null;
	}

	/**
	 * Compare new passwords.
	 *
	 * @param password the password
	 * @param reenterPassword the reenter password
	 * @return the string
	 */
	public static String compareNewPasswords(String password, String reenterPassword) {
		if (!password.equals(reenterPassword)) {
			return "different_passwords";
		}
		return null;
	}

	/**
	 * Compare old new passwords.
	 *
	 * @param oldpassword the oldpassword
	 * @param newPassword the new password
	 * @return the string
	 */
	public static String compareOldNewPasswords(String oldpassword, String newPassword) {
		if (oldpassword.equals(newPassword)) {
			return "old_new_passwords_must_be_different";
		}
		return null;
	}

	/**
	 * Validate registration.
	 *
	 * @param password the password
	 * @param reenterPassword the reenter password
	 * @param phone the phone
	 * @param email the email
	 * @return the string
	 */
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
			message = validateEmail(email);
		}
		return message;
	}

	/**
	 * Validate new contract.
	 *
	 * @param contract the contract
	 * @param firstName the first name
	 * @param secondName the second name
	 * @return the string
	 */
	public static String validateNewContract(String contract, String firstName, String secondName) {
		String message = checkEmptyFields(contract, firstName, secondName);
		if (message == null) {
			message = validateContract(contract);
		}
		if (message == null) {
			message = validateName(firstName);
		}
		if (message == null) {
			message = validateName(secondName);
		}
		return message;
	}

	/**
	 * Validate save user.
	 *
	 * @param account the account
	 * @param firstName the first name
	 * @param secondName the second name
	 * @param phone the phone
	 * @param email the email
	 * @return the string
	 */
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

	/**
	 * Validate request.
	 *
	 * @param firstName the first name
	 * @param email the email
	 * @param phone the phone
	 * @param address the address
	 * @return the string
	 */
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

	/**
	 * Validate tariff.
	 *
	 * @param name the name
	 * @param priceParameter the price parameter
	 * @param speedParameter the speed parameter
	 * @param trafficParameter the traffic parameter
	 * @return the string
	 */
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

	/**
	 * Validate contacts.
	 *
	 * @param phone the phone
	 * @param email the email
	 * @return the string
	 */
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

	/**
	 * Validate password update.
	 *
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @param reenterNewPassword the reenter new password
	 * @return the string
	 */
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

	/**
	 * Validate card details.
	 *
	 * @param number the number
	 * @param expirationMonth the expiration month
	 * @param expirationYear the expiration year
	 * @param securityCode the security code
	 * @param firstName the first name
	 * @param secondName the second name
	 * @param amount the amount
	 * @return the string
	 */
	public static String validateCardDetails(String number, String expirationMonth,
	        String expirationYear, String securityCode, String firstName, String secondName,
	        String amount) {
		String message = checkEmptyFields(number, expirationMonth, expirationYear, securityCode,
		        firstName, secondName, amount);
		if (message == null) {
			Matcher cardNumberMatcher = CARD_NUMBER_PATTERN.matcher(number);
			if (!cardNumberMatcher.matches()) {
				message = "invalid_card_number";
			}
		}
		if (message == null) {
			Matcher monthMatcher = MONTH_PATTERN.matcher(expirationMonth);
			if (!monthMatcher.matches()) {
				message = "invalid_month";
			}
		}
		if (message == null) {
			Matcher yearMatcher = YEAR_PATTERN.matcher(expirationYear);
			if (!yearMatcher.matches()) {
				message = "invalid_year";
			}
		}
		if (message == null) {
			Matcher securityCodeMatcher = SECURITY_CODE_PATTERN.matcher(securityCode);
			if (!securityCodeMatcher.matches()) {
				message = "invalid_security_code";
			}
		}
		if (message == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			String expirationDateString = "01-" + expirationMonth + "-20" + expirationYear;
			Date expirationDate;
			try {
				expirationDate = sdf.parse(expirationDateString);
				Date currentDate = new Date();
				if (currentDate.after(expirationDate)) {
					message = "expired_card";
				}
			} catch (ParseException e) {
				message = "invalid_expiration_date";
			}
		}
		if (message == null) {
			Matcher cardholderFirstNameMatcher = CARDHOLDER_NAME_PATTERN.matcher(firstName);
			Matcher cardholderSecondNameMatcher = CARDHOLDER_NAME_PATTERN.matcher(secondName);
			if (!cardholderFirstNameMatcher.matches() || !cardholderSecondNameMatcher.matches()) {
				message = "invalid_caldholder_name";
			}
		}
		if (message == null){
			message = validateBynAmount(amount);
		}
		return message;
	}
}

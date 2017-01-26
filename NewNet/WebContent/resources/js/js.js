function validateAuthenticationForm() {
	var form = document.forms['authentication'];

	if ((form.elements['account'].value == "")
			|| (form.elements['password'].value == "")) {
		alert("empty_fields");
		return false;
	}
	/*
	 * form.elements['account'].focus();
	 * 
	 * if (form.elements['password'].value == "") { alert("empty_fields");
	 * form.elements['password'].focus(); return false; }
	 */
}

function validateCheckAccountForm() {
	var form = document.forms['checkAccount'];

	if (form.elements['account'].value == "") {
		alert("empty_fields");
		form.elements['account'].focus();
		return false;
	}
	if (!/[1-9]\\d{11}/.test(form.elements['account'].value)) {
		alert("invalid_contract_number");
		form.elements['account'].focus();
		return false;
	}
}
function validateRequestForm() {
	var form = document.forms['request'];

	if ((form.elements['name'].value == "")
			|| (form.elements['phone'].value == "")
			|| (form.elements['email'].value == "")
			|| (form.elements['address'].value == "")) {
		alert("empty_fields");
		// form.elements['name'].focus();
		return false;
	}
	if (!/[А-ЯЁ][а-яё]{1,40}/.test(form.elements['name'].value)) {
		alert("invalid_name");
		form.elements['name'].focus();
		return false;
	}
	if (!/[1-9]\\d{8,12}/.test(form.elements['phone'].value)) {
		alert("invalid_phone");
		form.elements['phone'].focus();
		return false;
	}
	if (!/[A-z0-9_\\-.]+@[A-z0-9\\-.]+\\.[A-z]{2,10}/
			.test(form.elements['email'].value)) {
		alert("invalid_email");
		form.elements['email'].focus();
		return false;
	}
}

function validateEditTariffForm() {
	var form = document.forms['editTariff'];

	if ((form.elements['name'].value == "")
			|| (form.elements['price'].value == "")
			|| (form.elements['speed'].value == "")
			|| (form.elements['traffic'].value == "")) {
		alert("empty_fields");
		// form.elements['name'].focus();
		return false;
	}
	if (!/[A-ZА-ЯЁ]([A-zА-яЁё\\d ]){1,40}/.test(form.elements['name'].value)) {
		alert("invalid_tariff_name");
		form.elements['name'].focus();
		return false;
	}
	if (!/([1-9]{1}\\d*)|([1-9]\\d*)(\\.\\d{0,2})?/
			.test(form.elements['price'].value)) {
		alert("invalid_byn_amount");
		form.elements['price'].focus();
		return false;
	}
	if (!/[1-9]\\d?/.test(form.elements['speed'].value)) {
		alert("invalid_speed");
		form.elements['speed'].focus();
		return false;
	}
	if (!/0|([1-9]\\d?)/.test(form.elements['traffic'].value)) {
		alert("invalid_traffic");
		form.elements['traffic'].focus();
		return false;
	}
}

function validateEditUserForm() {
	var form = document.forms['editUser'];

	if ((form.elements['secondName'].value == "")
			|| (form.elements['firstName'].value == "")
			|| (form.elements['account'].value == "")
			|| (form.elements['phone'].value == "")
			|| (form.elements['email'].value == "")) {
		alert("empty_fields");
		// form.elements['name'].focus();
		return false;
	}
	if (!/[А-ЯЁ][а-яё]{1,40}/.test(form.elements['secondName'].value)) {
		alert("invalid_name");
		form.elements['secondName'].focus();
		return false;
	}
	if (!/[А-ЯЁ][а-яё]{1,40}/.test(form.elements['firstName'].value)) {
		alert("invalid_name");
		form.elements['firstName'].focus();
		return false;
	}
	if (!/[1-9]\\d{11}/.test(form.elements['account'].value)) {
		alert("invalid_contract_number");
		form.elements['account'].focus();
		return false;
	}
	if (!/[1-9]\\d{8,12}/.test(form.elements['phone'].value)) {
		alert("invalid_phone");
		form.elements['phone'].focus();
		return false;
	}
	if (!/[A-z0-9_\\-.]+@[A-z0-9\\-.]+\\.[A-z]{2,10}/
			.test(form.elements['email'].value)) {
		alert("invalid_email");
		form.elements['email'].focus();
		return false;
	}
}

function validatePaymentForm() {
	var form = document.forms['payment'];

	if ((form.elements['number'].value == "")
			|| (form.elements['expirationMonth'].value == "")
			|| (form.elements['expirationYear'].value == "")
			|| (form.elements['securityCode'].value == "")
			|| (form.elements['firstName'].value == "")
			|| (form.elements['secondName'].value == "")
			|| (form.elements['amount'].value == "")) {
		alert("empty_fields");
		// form.elements['name'].focus();
		return false;
	}
	if (!/\\d{16}/.test(form.elements['number'].value)) {
		alert("invalid_card_number");
		form.elements['number'].focus();
		return false;
	}
	if (!/0[1-9]|1[012]/.test(form.elements['expirationMonth'].value)) {
		alert("invalid_month");
		form.elements['expirationMonth'].focus();
		return false;
	}
	if (!/[1-2][0-9]/.test(form.elements['expirationYear'].value)) {
		alert("invalid_year");
		form.elements['expirationYear'].focus();
		return false;
	}
	// !!!!!!!!!!!!!!!!!!!!!expired date
	if (!/[1-2][0-9]/.test(form.elements['securityCode'].value)) {
		alert("invalid_security_code");
		form.elements['securityCode'].focus();
		return false;
	}
	if (!/[1-2][0-9]/.test(form.elements['securityCode'].value)) {
		alert("invalid_security_code");
		form.elements['securityCode'].focus();
		return false;
	}
	if (!/[A-z]{1,40}/.test(form.elements['firstName'].value)) {
		alert("invalid_cardholder_name");
		form.elements['firstName'].focus();
		return false;
	}
	if (!/[A-z]{1,40}/.test(form.elements['secondName'].value)) {
		alert("invalid_cardholder_name");
		form.elements['secondName'].focus();
		return false;
	}
	if (!/([1-9]{1}\\d*)|([1-9]\\d*)(\\.\\d{0,2})?/
			.test(form.elements['amount'].value)) {
		alert("invalid_byn_amount");
		form.elements['amount'].focus();
		return false;
	}
}

function validateChangePasswordForm() {
	var form = document.forms['changePassword'];

	if ((form.elements['oldPassword'].value == "")
			|| (form.elements['newPassword'].value == "")
			|| (form.elements['reenterNewPassword'].value == "")) {
		alert("empty_fields");
		// form.elements['name'].focus();
		return false;
	}
	if (!/(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{6,}/
			.test(form.elements['newPassword'].value)) {
		alert("invalid_password");
		form.elements['newPassword'].focus();
		return false;
	}
	if (form.elements['newPassword'].value == form.elements['oldPassword'].value) {
		alert("old_new_passwords_must_be_different");
		form.elements['newPassword'].focus();
		return false;
	}
	if (!(form.elements['newPassword'].value == form.elements['reenterNewPassword'].value)) {
		alert("different_passwords");
		form.elements['reenterNewPassword'].focus();
		return false;
	}
}

function validateSetContactsForm() {
	var form = document.forms['setContacts'];

	if ((form.elements['phone'].value == "")
			|| (form.elements['email'].value == "")) {
		alert("empty_fields");
		// form.elements['name'].focus();
		return false;
	}
	if (!/[1-9]\\d{8,12}/.test(form.elements['phone'].value)) {
		alert("invalid_phone");
		form.elements['phone'].focus();
		return false;
	}
	if (!/[A-z0-9_\\-.]+@[A-z0-9\\-.]+\\.[A-z]{2,10}/
			.test(form.elements['email'].value)) {
		alert("invalid_email");
		form.elements['email'].focus();
		return false;
	}
}

function validateRegistrationForm() {
	var form = document.forms['registration'];

	if ((form.elements['password'].value == "")
			|| (form.elements['reenterPassword'].value == "")
			|| (form.elements['email'].value == "")
			|| (form.elements['phone'].value == "")) {
		alert("empty_fields");
		// form.elements['name'].focus();
		return false;
	}
	if (!/[1-9]\\d{8,12}/.test(form.elements['phone'].value)) {
		alert("invalid_phone");
		form.elements['phone'].focus();
		return false;
	}
	if (!/[A-z0-9_\\-.]+@[A-z0-9\\-.]+\\.[A-z]{2,10}/
			.test(form.elements['email'].value)) {
		alert("invalid_email");
		form.elements['email'].focus();
		return false;
	}
	if (!(form.elements['password'].value == form.elements['reenterPassword'].value)) {
		alert("different_passwords");
		form.elements['reenterPassword'].focus();
		return false;
	}
}

function validateNewContractForm() {
	var form = document.forms['newContract'];

	if ((form.elements['secondName'].value == "")
			|| (form.elements['firstName'].value == "")
			|| (form.elements['contract'].value == "")) {
		alert("empty_fields");
		// form.elements['name'].focus();
		return false;
	}
	if (!/[А-ЯЁ][а-яё]{1,40}/.test(form.elements['secondName'].value)) {
		alert("invalid_name");
		form.elements['secondName'].focus();
		return false;
	}
	if (!/[А-ЯЁ][а-яё]{1,40}/.test(form.elements['firstName'].value)) {
		alert("invalid_name");
		form.elements['firstName'].focus();
		return false;
	}
	if (!/[1-9]\\d{11}/.test(form.elements['contract'].value)) {
		alert("invalid_contract_number");
		form.elements['contract'].focus();
		return false;
	}
}

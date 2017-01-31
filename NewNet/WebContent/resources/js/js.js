function checkNotEmpty(value) {
	if ((value == "")) {
		alert(emptyFieldsMessage);
		return false;
	}
	return true;
}
function validatePassword(value) {
	if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{6,}$/.test(value)) {
		alert(invalidPasswordMessage);
		return false;
	}
	return true;
}
function validateAccount(value) {
	if (!/^[1-9]\d{11}$/.test(value)) {
		alert(invalidContractNumberMessage);
		return false;
	}
	return true;
}
function validateName(value) {
	if (!/^[А-ЯЁ][а-яё]{1,40}$/.test(value)) {
		alert(invalidNameMessage);
		return false;
	}
	return true;
}
function validateEmail(value) {
	if (!/^[A-z0-9_\-.]+@[A-z0-9\-.]+\.[A-z]{2,10}$/.test(value)) {
		alert(invalidEmailMessage);
		return false;
	}
	return true;
}
function validatePhone(value) {
	if (!/^[1-9]\d{8,12}$/.test(value)) {
		alert(invalidPhoneMessage);
		return false;
	}
	return true;
}
function validateTariffName(value) {
	if (!/^[A-ZА-ЯЁ]([A-zА-яЁё\d ]){1,40}$/.test(value)) {
		alert(invalidTariffNameMessage);
		return false;
	}
	return true;
}
function validateBynAmount(value) {
	if (!/^([1-9]{1}\d*)|([1-9]\d*)(\.\d{0,2})?$/.test(value)) {
		alert(invalidBynAmountMessage);
		return false;
	}
	return true;
}
function validateSpeed(value) {
	if (!/^[1-9]\d?$/.test(value)) {
		alert(invalidSpeedMessage);
		return false;
	}
	return true;
}
function validateTraffic(value) {
	if (!/^0|([1-9]\d?)$/.test(value)) {
		alert(invalidTrafficMessage);
		return false;
	}
	return true;
}







function validateAuthenticationForm() {
	var form = document.forms['authentication'];
	return checkNotEmpty(form.elements['account'].value)
			&& checkNotEmpty(form.elements['password'].value);
}
function validateCheckAccountForm() {
	var form = document.forms['checkAccount'];
	if (!checkNotEmpty(form.elements['account'].value)) {
		form.elements['account'].focus();
		return false;
	}
	if (!validateAccount(form.elements['account'].value)) {
		form.elements['account'].focus();
		return false;
	}
	return true;
}
function validateRequestForm() {
	var form = document.forms['request'];
	if (!(checkNotEmpty(form.elements['name'].value)&& checkNotEmpty(form.elements['phone'].value)
			&& checkNotEmpty(form.elements['email'].value) && checkNotEmpty(form.elements['address'].value))) {
		return false;
	}
	if (!validateName(form.elements['name'].value)) {
		form.elements['name'].focus();
		return false;
	}
	if (!validatePhone(form.elements['phone'].value)) {
		form.elements['phone'].focus();
		return false;
	}
	if (!validateEmail(form.elements['email'].value)) {
		form.elements['email'].focus();
		return false;
	}
	return true;
}
function validateEditTariffForm() {
	var form = document.forms['editTariff'];
	if (!(checkNotEmpty(form.elements['name'].value)&& checkNotEmpty(form.elements['price'].value)
			&& checkNotEmpty(form.elements['speed'].value) && checkNotEmpty(form.elements['traffic'].value))) {
		return false;
	}
	if (!validateTariffName(form.elements['name'].value)) {
		form.elements['name'].focus();
		return false;
	}
	if (!validateBynAmount(form.elements['price'].value)) {
		form.elements['price'].focus();
		return false;
	}
	if (!validateSpeed(form.elements['speed'].value)) {
		form.elements['speed'].focus();
		return false;
	}
	if (!validateTraffic(form.elements['traffic'].value)) {
		form.elements['traffic'].focus();
		return false;
	}
	return true;
}

function validateEditUserForm() {
	var form = document.forms['editUser'];
	if (!(checkNotEmpty(form.elements['secondName'].value)&& checkNotEmpty(form.elements['firstName'].value)
			&& checkNotEmpty(form.elements['account'].value) && checkNotEmpty(form.elements['phone'].value)
			&& checkNotEmpty(form.elements['email'].value))) {
		return false;
	}
	if (!validateName(form.elements['secondName'].value) || !validateName(form.elements['firstName'].value)) {
		return false;
	}
	if (!validateAccount(form.elements['account'].value)) {
		form.elements['account'].focus();
		return false;
	}
	if (!validatePhone(form.elements['phone'].value)) {
		form.elements['phone'].focus();
		return false;
	}
	if (!validateEmail(form.elements['email'].value)) {
		form.elements['email'].focus();
		return false;
	}
	return true;
}
function validatePaymentForm() {
	var form = document.forms['payment'];
	if (!(checkNotEmpty(form.elements['number'].value)&& checkNotEmpty(form.elements['expirationMonth'].value)
			&& checkNotEmpty(form.elements['expirationYear'].value) && checkNotEmpty(form.elements['securityCode'].value)
			&& checkNotEmpty(form.elements['firstName'].value) && checkNotEmpty(form.elements['secondName'].value)
			&& checkNotEmpty(form.elements['amount'].value))) {
		return false;
	}
	if (!/^\d{16}$/.test(form.elements['number'].value)) {
		alert(invalidCardNumberMessage);
		form.elements['number'].focus();
		return false;
	}
	if (!/^0[1-9]|1[012]$/.test(form.elements['expirationMonth'].value)) {
		alert(invalidMonthMessage);
		form.elements['expirationMonth'].focus();
		return false;
	}
	if (!/^[1-2][0-9]$/.test(form.elements['expirationYear'].value)) {
		alert(invalidYearMessage);
		form.elements['expirationYear'].focus();
		return false;
	}
	if (!/^\d{3}$/.test(form.elements['securityCode'].value)) {
		alert(invalidSecurityCodeMessage);
		form.elements['securityCode'].focus();
		return false;
	}
	if (!/^[A-z]{1,40}$/.test(form.elements['firstName'].value) || !/^[A-z]{1,40}$/.test(form.elements['secondName'].value)) {
		alert(invalidCaldHolderNameMessage);
		return false;
	}
	if (!validateBynAmount(form.elements['amount'].value)) {
		form.elements['amount'].focus();
		return false;
	}
	return true;
}

function validateChangePasswordForm() {
	var form = document.forms['changePassword'];
	if (!(checkNotEmpty(form.elements['oldPassword'].value)&& checkNotEmpty(form.elements['newPassword'].value)
			&& checkNotEmpty(form.elements['reenterNewPassword'].value))) {
		return false;
	}
	if (!validatePassword(form.elements['newPassword'].value)) {
		form.elements['newPassword'].focus();
		return false;
	}
	if (form.elements['newPassword'].value == form.elements['oldPassword'].value) {
		alert(equalOldNewPasswordsMessage);
		form.elements['newPassword'].focus();
		return false;
	}
	if (!(form.elements['newPassword'].value == form.elements['reenterNewPassword'].value)) {
		alert(differentPasswordsMessage);
		form.elements['reenterNewPassword'].focus();
		return false;
	}
	return true;
}

function validateSetContactsForm() {
	var form = document.forms['setContacts'];
	if (!(checkNotEmpty(form.elements['phone'].value)&& checkNotEmpty(form.elements['email'].value))) {
		return false;
	}
	if (!validatePhone(form.elements['phone'].value)) {
		form.elements['phone'].focus();
		return false;
	}
	if (!validateEmail(form.elements['email'].value)) {
		form.elements['email'].focus();
		return false;
	}
	return true;
}

function validateRegistrationForm() {
	var form = document.forms['registration'];
	if (!(checkNotEmpty(form.elements['password'].value)&& checkNotEmpty(form.elements['reenterPassword'].value)
			&& checkNotEmpty(form.elements['email'].value) && checkNotEmpty(form.elements['email'].value))) {
		return false;
	}
	if (!validatePassword(form.elements['password'].value)) {
		form.elements['password'].focus();
		return false;
	}
	if (!(form.elements['password'].value == form.elements['reenterPassword'].value)) {
		alert(differentPasswordsMessage);
		form.elements['reenterPassword'].focus();
		return false;
	}
	if (!validatePhone(form.elements['phone'].value)) {
		form.elements['phone'].focus();
		return false;
	}
	if (!validateEmail(form.elements['email'].value)) {
		form.elements['email'].focus();
		return false;
	}
	return true;
}

function validateNewContractForm() {
	var form = document.forms['newContract'];
	if (!(checkNotEmpty(form.elements['secondName'].value)&& checkNotEmpty(form.elements['firstName'].value)
			&& checkNotEmpty(form.elements['contract'].value))) {
		return false;
	}
	if (!validateName(form.elements['secondName'].value) || !validateName(form.elements['firstName'].value)) {
		return false;
	}
	if (!validateAccount(form.elements['contract'].value)) {
		form.elements['contract'].focus();
		return false;
	}
	return true;
}

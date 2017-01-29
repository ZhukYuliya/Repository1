<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<title>${lastRequestUrl} NewNet: <fmt:message key="payment" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<main>
	<section id="edit">
	<span>
		<fmt:message key="current_balance" />
		:${user.accountBalance} ${byn}<br>
	</span>
	<span>
		<fmt:message key="card_details" />
	</span>
	<form action="${contextPath}/controller" method="post" name="payment" 
			onsubmit="return validatePaymentForm()">
		<input type="hidden" name="command" value="pay" />
		<table>
			<tr>
				<td><fmt:message key="card_number" /></td>
				<td><input name="number" value=""
					placeholder="XXXX XXXX XXXX XXXX" type="text" class="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="expiration_date" /></td>
				<td><input name="expirationMonth" value="" type="text"
					placeholder="MM" class="text" /> / <input name="expirationYear"
					value="" type="text" placeholder="YY" class="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="security_code" /></td>
				<td><input name="securityCode" value="" placeholder="XXX"
					type="text" class="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="cardholder_name" /></td>
				<td><input name="firstName" value="" type="text"
					placeholder=<fmt:message key="first_name" /> class="text" /> <input
					name="secondName" value="" type="text"
					placeholder=<fmt:message key="second_name" /> class="text" /></td>
			</tr>
		</table>
		<br>
		<fmt:message key="amount_to_pay" />
		:<input name="amount" value="" type="text" class="text" /> <input
			type="submit" class="submit" value='<fmt:message key="pay_button"/> ' />
	</form>
	</section></main>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>



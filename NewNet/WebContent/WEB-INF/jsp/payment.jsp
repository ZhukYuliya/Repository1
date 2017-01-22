<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>NewNet: <fmt:message key="payment" /></title>
</head>
<body>

	<%@include file="/WEB-INF/jsp_fragments/header.jsp"%>
	<p>
		<fmt:message key="current_balance" />:${user.accountBalance}<br>
	</p>
	<p>
		<fmt:message key="card_details" />
	</p>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="pay" />
		<table>
		<tr>
			<td><fmt:message key="card_number" /></td>
			<td><input name="number" value=""  type="text" class="text" /></td>
		</tr>
		<tr>
			<td><fmt:message key="expiration_date" /></td>
			<td><input name="expirationMonth" value="" type="text"
				placeholder="MM" class="text" /> / <input name="expirationYear"
				value="" type="text" placeholder="YY" class="text" /></td>
		</tr>
		<tr>
			<td><fmt:message key="security_code" /></td>
			<td><input name="securityCode" value="" placeholder="XXX"type="text" class="text" /></td>
		</tr>
		<tr>
			<td><fmt:message key="cardholder_name" /></td>
			<td><input name="firstName" value="" type="text"
				placeholder=<fmt:message key="first_name" /> class="text" /> <input
				name="secondName" value="" type="text"
				placeholder=<fmt:message key="second_name" /> class="text" /></td>
		</tr>
		</table><br>
		<fmt:message key="amount_to_pay" />:<input name="amount" value="" type="text" class="text" />
		
			<input type="submit" class="submit"
				value='<fmt:message key="pay_button"/> ' />
	</form>
</body>
</html>



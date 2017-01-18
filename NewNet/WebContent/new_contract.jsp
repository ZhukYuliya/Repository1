<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Title</title>
</head>
<body>

	<%@include file="WEB-INF/jsp_fragments/header.jsp"%>

	<h1>
		<fmt:message key="new_user" />
	</h1>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="add_new_contract" />
		<tr>
			<td><fmt:message key="first_name" /></td>
			<td><input name="firstName" value="" type="text" class="text" /></td>
		</tr>
		<tr>
			<td><fmt:message key="second_name" /></td>
			<td><input name="secondName" value="" type="text" class="text" /></td>
		</tr>
		<tr>
			<td><fmt:message key="account_number" /></td>
			<td><input name="account" value="" type="text" class="text" /></td>
		</tr>
		<input type="submit" class="submit"
			value='<fmt:message key="pay_button"/> ' />
	</form>
	<c:if test="${not empty paymentMessage}">
		<fmt:message key="${payment_message}" />
	</c:if>

</body>
</html>



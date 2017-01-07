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
		<fmt:message key="hello" />
		, ${user.firstName} ${user.secondName}
	</h1>
	
	<p><fmt:message key="account_info" /></p>
	
	<table border="1">
		<tr>
			<td><fmt:message key="account_number" /></td>
			<td><fmt:message key="account_balance" /></td>
			<td><fmt:message key="current_tariff" /></td>
			<td><fmt:message key="is_blocked" /></td>
		</tr>
		<tr>
			<td>${user.account.number}</td>
			<td>${user.account.balance}</td>
			<td>${user.account.tariff.name}</td>
			<td>${user.account.blocked}</td>
		</tr>
	</table>


	<form action="controller" method="get">
		<input type="hidden" name="command" value="subsribe_tariff" /> 
		<input type="submit" value='<fmt:message key="subsribe_for_tariff"/> ' /> <br />
		<select name="newTariff">
			<option><fmt:message key="choose_tariff" /></option>
			<c:forEach var="tariff" items="${tariffs}">
        		<option value="${tariff.id}"> ${tariff.name}</option>
     		 </c:forEach>
		</select>
	</form>

</body>
</html>



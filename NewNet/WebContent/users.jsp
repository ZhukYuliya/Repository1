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
	<br>
	<br>
	<table border="1">
		<tr>
			<td>ID</td>
			<td><fmt:message key="first_name" /></td>
			<td><fmt:message key="second_name" /></td>
			<td><fmt:message key="account_number" /></td>
			<td><fmt:message key="account_balance" /></td>
			<td><fmt:message key="tariff" /></td>
			<td><fmt:message key="role" /></td>
			<td><fmt:message key="blocked" /></td>
			<td><fmt:message key="email" /></td>
			<td><fmt:message key="phone" /></td>			
		</tr>
		<c:forEach var="user" items="${usersList}">
			<tr>
				<td><a href="<c:url value='/controller?command=GET_USER&id=${user.id}'/>">${user.id}</a></td>
				<td>${user.firstName}</td>
				<td>${user.secondName}</td>
				<td>${user.account}</td>
				<td>${user.accountBalance}</td>
				<td>${user.tariff.name}</td>
				<td>${user.role.name}</td>
				<td>${user.banned}</td>
				<td>${user.email}</td>
				<td>${user.phone}</td>				
			</tr>
		</c:forEach>
	</table>
	<%@include file="WEB-INF/jsp_fragments/footer.jsp"%>

</body>
</html>



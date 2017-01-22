<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>NewNet: User editing</title>
</head>
<body>

	<%@include file="/WEB-INF/jsp_fragments/header.jsp"%>

	<h1>
		<fmt:message key="edit_user" />
	</h1>
	<p>
		<fmt:message key="editable_info" />
	</p>
	<form action="controller" method="post">
		<input name="id" value="${user.id}" readonly type="text" class="text" /> <input
			name="first_name" value="${user.firstName}" type="text" class="text" />
		<input name="second_name" value="${user.secondName}" type="text"
			class="text" /> <input name="account" value="${user.account}"
			type="text" class="text" /> <input name="phone"
			value="${user.phone}" type="text" class="text" /> <input
			name="email" value="${user.email}" type="text" class="text" /> <select
			name="role">
			<option value="1"><fmt:message key="admin" /></option>
			<option value="2"><fmt:message key="operator" /></option>
			<option value="3"><fmt:message key="customer" /></option>
		</select> <select name="banned">
			<option value="true"><fmt:message key="ban" /></option>
			<option value="false"><fmt:message key="unban" /></option>
		</select> <select name="tariff">
			<option value="${user.tariff.id}">${tariff.name}</option>
			<c:forEach var="tariff" items="${tariffsList}">
				<option value="${tariff.id}">${tariff.name}</option>
			</c:forEach>
		</select> <input type="hidden" name="command" value="save_user" /> <input
			type="submit" class="submit"
			value='<fmt:message key="save_button"/> ' />
	</form>
</body>
</html>



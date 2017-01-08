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
		<fmt:message key="change_details" />
	</h1>

	<h2>
		<fmt:message key="change_password" />
	</h2>
	<form action="controller" method="post">
			<input name="password" value="" placeholder='<fmt:message key="old_password" />'
				class="text" /> 
				<input name="newPassword" value=""
				placeholder='<fmt:message key="new_password" />' class="text" /> 
				<input name="reenterNewPassword"
				value="" placeholder='<fmt:message key="reenter_new_password" />' class="text" /> 
				<input type="submit" class="submit" value='<fmt:message key="change_button"/> ' />
		</form>
		
	<h2>
		<fmt:message key="change_contacts" />
	</h2>
	<p><fmt:message key="current_contacts" />: ${user.phone}, ${user.email}</p>
	<form action="controller" method="post">
			<input name="newPhone" value="${user.phone}" placeholder='<fmt:message key="new_phone" />'
				class="text" /> 
				<input name="newEmail" value="${user.email}"
				placeholder='<fmt:message key="new_email" />' class="text" /> 
				<input type="submit" class="submit" value='<fmt:message key="change_button"/> ' />
		</form>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<title>NewNet: <fmt:message key="personal_details" /></title>
</head>
<body>

	<%@include file="/WEB-INF/jspf/header.jspf"%>
<main>
<section id="edit">
	<h1>
		<fmt:message key="change_details" />
	</h1>

	<h2>
		<fmt:message key="change_password" />
	</h2>
	<form action="${contextPath}/controller" method="post" name="changePassword" 
			onsubmit="return validateChangePasswordForm()">
		<input name="oldPassword" value="" type="password"
			placeholder='<fmt:message key="old_password" />' class="text" /> <input
			name="newPassword" value="" type="password"
			placeholder='<fmt:message key="new_password" />' class="text" /> <input
			name="reenterNewPassword" value=""
			placeholder='<fmt:message key="reenter_new_password" />'
			type="password" class="text" /> <input type="hidden" name="command"
			value="set_password" /> <input type="submit" class="submit"
			value='<fmt:message key="change_button"/> ' />
	</form>
	<c:if test="${not empty setPasswordMessage}">
		<fmt:message key="${setPasswordMessage}" />
	</c:if>
	<h2>
		<fmt:message key="change_contacts" />
	</h2>
	<p>
		<fmt:message key="current_contacts" />
		: ${user.phone}, ${user.email}
	</p>
	<form action="${contextPath}/controller" method="post" name="setContacts" 
			onsubmit="return validateSetContactsForm()">
		<input name="phone" value="${user.phone}"
			placeholder='<fmt:message key="new_phone" />' class="text" /> <input
			name="email" value="${user.email}"
			placeholder='<fmt:message key="new_email" />' class="text" /> <input
			type="hidden" name="command" value="set_contacts" /> <input
			type="submit" class="submit"
			value='<fmt:message key="change_button"/> ' />
	</form>
	<c:if test="${not empty setContactsMessage}">
		<fmt:message key="${setContactsMessage}" />
	</c:if>
	</section>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
</main>
</body>
</html>



<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<title>NewNet:<fmt:message key="registration" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<main>
	<section id="edit">
		<h1>
			<fmt:message key="welcome" />, ${user.firstName} ${user.secondName}.<br>
			<fmt:message key="fill_in" />:
		</h1>
		<form action="${contextPath}/controller" method="post"
			name="registration" onsubmit="return validateRegistrationForm()">
			<table>
				<tr>
					<td><fmt:message key="password_format" /></td>
					<td><input type="password" name="password" value=""
						placeholder='<fmt:message key="password" />' class="text" /></td>
				</tr>
				<tr>
					<td><fmt:message key="reenter_password" /></td>
					<td><input type="password" name="reenterPassword" value=""
						placeholder='<fmt:message key="reenter_password" />' class="text" /></td>
				</tr>
				<tr>
					<td><fmt:message key="phone_format" /></td>
					<td><input name="phone" value=""
						placeholder='<fmt:message key="phone" />' class="text" /></td>
				</tr>
				<tr>
					<td><fmt:message key="email" /></td>
					<td><input name="email" value=""
						placeholder='<fmt:message key="email" />' class="text" /></td>
				</tr>
			</table>
			<input type="hidden" name="userId" value="${user.id}" /> <input
				type="hidden" name="firstName" value="${user.firstName}" /> <input
				type="hidden" name="secondName" value="${user.secondName}" /> <input
				type="hidden" name="command" value="registration" /> <input
				type="submit" class="submit"
				value='<fmt:message key="save_button"/> ' />
		</form>
		<c:if test="${not empty registrationMessage}">
			<fmt:message key="${registrationMessage}" />
		</c:if>
	</section>
	<%@include file="/WEB-INF/jspf/footer.jspf"%> 
	</main>
</body>
</html>



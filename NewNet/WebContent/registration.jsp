<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>NewNet:<fmt:message key="account_creation" /></title>
</head>
<body>

	<%@include file="WEB-INF/jsp_fragments/header.jsp"%>

	<h1>
		<fmt:message key="welcome" />
		, ${user.firstName} ${user.secondName},
		<fmt:message key="fill_in" />
		:
	</h1>
	<form action="controller" method="post">
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
				<input type="hidden" name="command" value="registration" />
				<input type="submit" class="submit"
					value='<fmt:message key="save_button"/> ' />
	</form>
	<c:if test="${not empty registrationMessage}">
					<fmt:message key="${registrationMessage}" />
				</c:if>
	<%@include file="WEB-INF/jsp_fragments/footer.jsp"%>

</body>
</html>



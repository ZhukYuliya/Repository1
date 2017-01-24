<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp_fragments/head_tag.jsp"%>
<title>NewNet: <fmt:message key="user_editing" /></title>
</head>
<body>

	<%@include file="/WEB-INF/jsp_fragments/header.jsp"%>
<main>

	<h1>
		<fmt:message key="edit_user" />
	</h1>
	<p>
		<fmt:message key="editable_info" />
	</p>
	<form action="${contextPath}/controller" method="post">
		<table border="1">
			<tr>
				<td><fmt:message key="id" /></td>
				<td><input name="id" value="${user.id}" readonly type="text"
					class="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="second_name" /></td>
				<td><input name="second_name" value="${user.secondName}"
					type="text" class="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="first_name" /></td>
				<td><input name="first_name" value="${user.firstName}"
					type="text" class="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="account_number" /></td>
				<td><input name="account" value="${user.account}" type="text"
					class="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="phone" /></td>
				<td><input name="phone" value="${user.phone}" type="text"
					class="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="email" /></td>
				<td><input name="email" value="${user.email}" type="text"
					class="text" /></td>
			</tr>
			<tr>
				<td><fmt:message key="block" />: <c:when
						test="${user.blocked}">
						<fmt:message key="${blocked}" />
					</c:when> <c:otherwise>
						<fmt:message key="${not_blocked}" />
					</c:otherwise></td>
				<td><select name="blocked">
						<option value="true"><fmt:message key="ban" /></option>
						<option value="false"><fmt:message key="unban" /></option>
				</select></td>
			</tr>
			<tr>
				<td><fmt:message key="tariff" />.<fmt:message
						key="current_tariff" />:${user.tariff.name}</td>
				<td><select name="tariff">
						<option value="${user.tariff.id}">${tariff.name}</option>
						<c:forEach var="tariff" items="${tariffsList}">
							<option value="${tariff.id}">${tariff.name}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input type="hidden" name="command" value="save_user" /> <input
			type="submit" class="submit"
			value='<fmt:message key="save_button"/> ' />
	</form>
	<c:if test="${not empty userEditingMessage}">
	<fmt:message key="${userEditingMessage}" />
</c:if>
	
	<form action="${contextPath}/controller" method="get">
	<input type="hidden" name="command" value="show_users" /> <input
		type="submit" value='<fmt:message key="back_to_users"/> ' />
</form>

	<%-- <select
			name="role">
			<option value="1"><fmt:message key="admin" /></option>
			<option value="2"><fmt:message key="operator" /></option>
			<option value="3"><fmt:message key="customer" /></option>
		</select> --%>
	<%@include file="/WEB-INF/jsp_fragments/footer.jsp"%>
	</main>
	
</body>
</html>



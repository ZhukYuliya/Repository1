<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<title>NewNet: <fmt:message key="user_editing" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<main>
	<section id="edit">
		<h1>
			<fmt:message key="edit_user" />
		</h1>
		<form action="${contextPath}/controller" method="post" name="editUser"
			onsubmit="return validateEditUserForm()">
			<table>
				<tr>
					<td>ID</td>
					<td><input name="id" value="${requestScope.user.id}"
						placeholder="${requestScope.user.id}" readonly type="text"
						class="text" /></td>
				</tr>
				<tr>
					<td><fmt:message key="second_name" /> *</td>
					<td><input name="secondName"
						value="${requestScope.user.secondName}"
						placeholder="${requestScope.user.secondName}" type="text"
						class="text" /></td>
				</tr>
				<tr>
					<td><fmt:message key="first_name" /> *</td>
					<td><input name="firstName"
						value="${requestScope.user.firstName}"
						placeholder="${requestScope.user.firstName}" type="text"
						class="text" /></td>
				</tr>
				<tr>
					<td><fmt:message key="account_number" /> *</td>
					<td><input name="account" value="${requestScope.user.account}"
						placeholder="${requestScope.user.account}" type="text"
						class="text" /></td>
				</tr>
				<tr>
					<td><fmt:message key="phone" /> *</td>
					<td><input name="phone" value="${requestScope.user.phone}"
						placeholder="${requestScope.user.phone}" type="text" class="text" /></td>
				</tr>
				<tr>
					<td><fmt:message key="email" /> *</td>
					<td><input name="email" value="${requestScope.user.email}"
						placeholder="${user.email}" type="text" class="text" /></td>
				</tr>
				<tr>
					<td><fmt:message key="block" />: <c:choose>
							<c:when test="${requestScope.user.blocked}">
								<fmt:message key="blocked" />
							</c:when>
							<c:otherwise>
								<fmt:message key="not_blocked" />
							</c:otherwise>
						</c:choose></td>
					<td><select name="blocked">
							<option value="true"><fmt:message key="ban" /></option>
							<option value="false"><fmt:message key="unban" /></option>
					</select></td>
				</tr>
				<tr>
					<td><fmt:message key="tariff" />
					<td><select name="tariff">
							<option value="${requestScope.user.tariff.id}">${requestScope.user.tariff.name}</option>
							<c:forEach var="tariff" items="${tariffsList}">
								<c:if
									test="${!tariff.inactive || tariff.inactive && user.isAdmin()}">
									<option value="${tariff.id}">${tariff.name}</option>
								</c:if>
							</c:forEach>
					</select></td>
				</tr>
			</table>
			<input type="hidden" name="command" value="save_user" /> <input
				type="submit" class="submit"
				value='<fmt:message key="save_button"/> ' />
		</form>

		<form action="${contextPath}/controller" method="get">
			<input type="hidden" name="command" value="show_users" /> <input
				type="submit" value='<fmt:message key="back_to_users"/>' />
		</form>
	</section>
	<%@include file="/WEB-INF/jspf/footer.jspf"%> 
	</main>
</body>
</html>



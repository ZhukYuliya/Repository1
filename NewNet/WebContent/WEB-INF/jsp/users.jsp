<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp_fragments/head_tag.jsp"%>
<title>NewNet: <fmt:message key="users" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jsp_fragments/header.jsp"%>
	<main> <br>
	<c:if test="${not empty userEditingMessage}">
		<fmt:message key="${userEditingMessage}" />
	</c:if> <br>
	<table border="1">
		<tr>
			<td><fmt:message key="click_user_id" /></td>
			<td><fmt:message key="second_name" /></td>
			<td><fmt:message key="first_name" /></td>
			<td><fmt:message key="account_number" /></td>
			<td><fmt:message key="account_balance" />, ${byn}</td>
			<td><fmt:message key="tariff" /></td>
			<td><fmt:message key="role" /></td>
			<td><fmt:message key="blocked" /></td>
			<td><fmt:message key="email" /></td>
			<td><fmt:message key="phone" /></td>
		</tr>
		<c:forEach var="user" items="${usersList}">
			<tr>
				<td><a
					href="<c:url value='/controller?command=SHOW_USER&id=${user.id}'/>">${user.id}</a></td>
				<td>${user.secondName}</td>
				<td>${user.firstName}</td>
				<td>${user.account}</td>
				<td>${user.accountBalance}</td>
				<td>${user.tariff.name}</td>
				<td>${user.role.name}
				<%-- <c:choose>
						<c:when test="${user.isAdmin()}">
							<fmt:message key="${admin}" />
						</c:when>
						<c:when test="${user.isOperator()}">
							<fmt:message key="${operator}" />
						</c:when>
						<c:otherwise>
							<fmt:message key="${customer}" />
						</c:otherwise>
					</c:choose> --%></td>
				<td><c:choose>
						<c:when test="${user.blocked}">
							<fmt:message key="${blocked}" />
						</c:when>
						<c:otherwise>
							<fmt:message key="${not_blocked}" />
						</c:otherwise>
					</c:choose></td>
				<td>${user.email}</td>
				<td>${user.phone}</td>
			</tr>
		</c:forEach>
	</table>
	<%@include file="/WEB-INF/jsp_fragments/footer.jsp"%>
	</main>
</body>
</html>


<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<title>NewNet: <fmt:message key="users" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<main>
	<section id="view">
		<h1>
			<fmt:message key="click_user_id" />
			.
			<fmt:message key="admin_ineditable" />
		</h1>
		<c:if test="${not empty param.userEditingMessage}">
			<span><fmt:message key="${param.userEditingMessage}" /></span>
		</c:if>
		<table>
			<tr>
				<td>ID</td>
				<td><fmt:message key="second_name" /></td>
				<td><fmt:message key="first_name" /></td>
				<td><fmt:message key="account_number" /></td>
				<td><fmt:message key="account_balance" />, <fmt:message
						key="currency" /></td>
				<td><fmt:message key="tariff" /></td>
				<td><fmt:message key="role" /></td>
				<td><fmt:message key="block" /></td>
				<td><fmt:message key="email" /></td>
				<td><fmt:message key="phone" /></td>
			</tr>
			<c:forEach var="user" items="${usersList}">
				<tr>
					<td><c:choose>
							<c:when test="${user.isAdmin()}">${user.id}
						</c:when>
							<c:when test="${user.isOperator() || user.isCustomer()}">
								<a
									href="<c:url value='/controller?command=SHOW_USER&id=${user.id}'/>">${user.id}</a>
							</c:when>
						</c:choose></td>
					<td>${user.secondName}</td>
					<td>${user.firstName}</td>
					<td>${user.account}</td>
					<td>${user.accountBalance}</td>
					<td>${user.tariff.name}</td>
					<td><c:choose>
							<c:when test="${user.isAdmin()}">
								<fmt:message key="admin" />
							</c:when>
							<c:when test="${user.isOperator()}">
								<fmt:message key="operator" />
							</c:when>
							<c:otherwise>
								<fmt:message key="customer" />
							</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${user.blocked}">
								<fmt:message key="blocked" />
							</c:when>
							<c:otherwise>
								<fmt:message key="not_blocked" />
							</c:otherwise>
						</c:choose></td>
					<td>${user.email}</td>
					<td>${user.phone}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pagination">
			<span><c:forEach var="i" begin="1" end="${totalPages}"
					step="1">
					<a href="<c:url value='/controller?command=SHOW_USERS&page=${i}'/>">${i}
					</a>
				</c:forEach></span>
		</div>
	</section>
	<%@include file="/WEB-INF/jspf/footer.jspf"%> </main>
</body>
</html>



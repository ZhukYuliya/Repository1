<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<title>NewNet: <fmt:message key="requests" /></title>
</head>
<body>

	<%@include file="/WEB-INF/jspf/header.jspf"%>
<main>
<section id="view">
	<c:if test="${not empty changeStatusMessage}">
		<fmt:message key="${changeStatusMessage}" />
	</c:if>
	
	<table border="1">
		<tr>
			<td>ID</td>
			<td><fmt:message key="first_name" /></td>
			<td><fmt:message key="email" /></td>
			<td><fmt:message key="phone" /></td>
			<td><fmt:message key="address" /></td>
			<td><fmt:message key="request_status" /></td>
		</tr>
		<c:forEach var="request" items="${requestsList}">
			<tr>
				<td>${request.id}</td>
				<td>${request.firstName}</td>
				<td>${request.email}</td>
				<td>${request.phone}</td>
				<td>${request.address}</td>
				<td>
				<c:choose>
						<c:when test="${request.isNew()}">
						<fmt:message key="new_request" />
							<form action="${contextPath}/controller" method="post">
								<input type="hidden" name="id" value="${request.id}" /> <input
									type="hidden" name="status" value="AFTER_CALL" /> <input type="hidden"
									name="command" value="set_request_status" /> <input
									type="submit" value='<fmt:message key="mark_after_call"/> ' />
							</form>
						</c:when>
						<c:when test="${request.isAfterCall()}">
						<fmt:message key="after_call_request" />
							<form action="${contextPath}/controller" method="post">
								<input type="hidden" name="id" value="${request.id}" /> <input
									type="hidden" name="status" value="AFTER_CONTRACT" /> <input type="hidden"
									name="command" value="set_request_status" /> <input
									type="submit" value='<fmt:message key="mark_after_contract"/> ' />
							</form>
						</c:when>
						<c:otherwise> <fmt:message key="after_contract_request" /> </c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
</section>
	<%@include file="/WEB-INF/jspf/account.jspf"%>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
	</main>
</body>
</html>



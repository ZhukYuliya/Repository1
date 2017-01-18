<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>NewNet: <fmt:message key="requests" /></title>
</head>
<body>

	<%@include file="WEB-INF/jsp_fragments/header.jsp"%>


	<table border="1">
		<tr>
			<td>ID</td>
			<td><fmt:message key="first_name" /></td>
			<td><fmt:message key="email" /></td>
			<td><fmt:message key="phone" /></td>
			<td><fmt:message key="address" /></td>
			<td><fmt:message key="request_status" /></td>
			<td><fmt:message key="change_status" /></td>
		</tr>
		<c:forEach var="request" items="${requestsList}">
			<tr>
				<td>${request.id}</td>
				<td>${request.firstName}</td>
				<td>${request.email}</td>
				<td>${request.phone}</td>
				<td>${request.address}</td>
				<td>${request.status}</td>
				<td><c:choose>
						<c:when test="${request.status == 1}">
							<select name="newStatus">
								<option value="2">2</option>
								<option value="3">3</option>
							</select>
							<a href= "/controller?command=set_request_status&id=request.id&newStatus="value>
						</c:when>
						<c:when test="${request.status == 2}">
							<option value="3">3</option>
							<a href= "/controller?command=set_request_status&newStatus=3">
						</c:when>
						<c:otherwise> - </c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>


	<%@include file="WEB-INF/jsp_fragments/account.jsp"%>

	<%@include file="WEB-INF/jsp_fragments/footer.jsp"%>
</body>
</html>



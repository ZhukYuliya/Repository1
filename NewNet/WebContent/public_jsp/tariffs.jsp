<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp_fragments/head_tag.jsp"%>
<title>NewNet: <fmt:message key="tariffs" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jsp_fragments/header.jsp"%>
	<main>
	<br>
	<br>
	<table border="1">
		<tr>
			<c:if test="${user.isAdmin()}">
				<td><fmt:message key="click_tariff_id" /></td>
			</c:if>
			<td><fmt:message key="name" /></td>
			<td><fmt:message key="price" />, BYN</td>
			<td><fmt:message key="speed" /></td>
			<td><fmt:message key="traffic" /></td>
			<c:if test="${user.isAdmin()}">
				<td><fmt:message key="activity" /></td>
			</c:if>
		</tr>
		<c:forEach var="tariff" items="${tariffsList}">
			<c:if test="${user.isAdmin()&& tariff.inactive || !tariff.inactive}">
				<tr>
					<c:if test="${user.isAdmin()}">
						<td><a
							href="<c:url value='/controller?command=SHOW_TARIFF&id=${tariff.id}'/>">${tariff.id}</a></td>
					</c:if>
					<td>${tariff.name}</td>
					<td>${tariff.price}</td>
					<td>${tariff.speed}</td>
					<td><c:choose>
							<c:when test="${tariff.traffic == 0}">
								<fmt:message key="unlim" />
							</c:when>
							<c:otherwise>
     						  ${tariff.traffic} <fmt:message key="gb" />
							</c:otherwise>
						</c:choose></td>
					<c:if test="${user.isAdmin()}">
						<td><c:choose>
								<c:when test="${tariff.inactive}">
									<fmt:message key="inactive" />
								</c:when>
								<c:otherwise>
									<fmt:message key="active" />
								</c:otherwise>
							</c:choose></td>
					</c:if>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<br>
	<br>
	<br>
	<c:if test="${user.isAdmin()}">
		<form action="${contextPath}/controller" method="get">
			<input type="hidden" name="command" value="show_tariff" /> <input
				type="submit" value='<fmt:message key="add_tariff"/> ' />
		</form>
	</c:if>
	<c:if test="${not empty saveTariffMessage}">
		<fmt:message key="${saveTariffMessage}" />
	</c:if>
	<br>
	<c:if test="${not empty sessionScope.user}">
	<fmt:message key="change_subscription"/>
		<fmt:message key="choose_tariff" />:
		<form action="${contextPath}/controller" method="post">
			<select name="newTariff">
				<c:forEach var="tariff" items="${tariffsList}">
					<c:if
						test="${user.isAdmin()&& tariff.inactive || !tariff.inactive}">
						<option value="${tariff.id}">${tariff.name}</option>
					</c:if>
				</c:forEach>
			</select> <input type="hidden" name="command" value="subscribe" /> <input
				type="submit" value='<fmt:message key="subsribe_for_tariff"/> ' />
		</form>
	</c:if>

	<%@include file="/WEB-INF/jsp_fragments/footer.jsp"%>
</main>
</body>
</html>


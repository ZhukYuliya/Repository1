<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
	<title>NewNet: <fmt:message key="tariffs" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<main>
	<h1>
		<fmt:message key="newnet_tariffs" />
	</h1>
	<section id="view">
		<table>
			<tr>
				<c:if test="${user.isAdmin()}">
					<td><fmt:message key="click_tariff_id" /></td>
				</c:if>
				<td><fmt:message key="tariff_name" /></td>
				<td><fmt:message key="price" />, <fmt:message key="currency" /></td>
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
		<div class="options">
			<c:if test="${user.isAdmin()}">
				<form action="${contextPath}/controller" method="get">
					<input type="hidden" name="command" value="show_tariff" /> <input
						type="submit" value='<fmt:message key="add_tariff"/> ' />
				</form>
			</c:if>
			<c:if test="${not empty param.saveTariffMessage}">
				<span><fmt:message key="${param.saveTariffMessage}" /></span>
			</c:if>
			<br>
			<c:if test="${not empty sessionScope.user}"><br>
				<span><fmt:message key="change_subscription" /> <fmt:message
						key="choose_tariff" />:</span>
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
		</div>
	</section>
	<%@include file="/WEB-INF/jspf/footer.jspf"%> 
	</main>
</body>
</html>



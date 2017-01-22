<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>NewNet: <fmt:message key="tariffs" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jsp_fragments/header.jsp"%>
	<br>
	<br>
	<table border="1">
		<tr>
			<c:if test="${user.role.id !=3}">
				<td>ID</td>
			</c:if>
			<td><fmt:message key="name" /></td>
			<td><fmt:message key="price" />, BYN</td>
			<td><fmt:message key="speed" /></td>
			<td><fmt:message key="traffic" /></td>
			<c:if test="${user.role.id !=3}">
				<td><fmt:message key="activity" /></td>
			</c:if>
		</tr>
		<c:forEach var="tariff" items="${tariffsList}">
				<tr>
					<c:if test="${user.role.id ==2}">
						<td>${tariff.id}</td>
						</c:if>
						<c:if test="${user.role.id ==1}">
						<td><a href="<c:url value='/controller?command=SHOW_TARIFF&id=${tariff.id}'/>">${tariff.id}</a></td>
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
					<c:if test="${user.role.id !=3}">
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
		</c:forEach>
	</table>
	<br>
	<br>
	<br>
	<c:if test="${user.role.id == 1}">
		<a href="${context}/edit_tariff.jsp"><fmt:message key="add_tariff" /></a>
	</c:if>
		<c:if test="${not empty saveTariffMessage}">
		<fmt:message key="${saveTariffMessage}" />
	</c:if>
	<br>
	<fmt:message key="choose_tariff" />:
	<c:if test="${not empty sessionScope.user}">
		<form action="controller" method="post">
			<select name="newTariff">
				<c:forEach var="tariff" items="${tariffsList}">
					<option value="${tariff.id}">${tariff.name}</option>
				</c:forEach>
			</select> <input type="hidden" name="command" value="subscribe" /> <input
				type="submit" value='<fmt:message key="subsribe_for_tariff"/> ' />
		</form>
	</c:if>
	
	<%@include file="/WEB-INF/jsp_fragments/footer.jsp"%>

</body>
</html>



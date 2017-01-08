<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Title</title>
</head>
<body>
	<%@include file="WEB-INF/jsp_fragments/header.jsp"%>
	<br>
	<br>
	<table border="1">
		<tr>
			<c:if test="${user.role.id != 3}">
				<td><fmt:message key="id" /></td>
			</c:if>
			<td><fmt:message key="name" /></td>
			<td><fmt:message key="price" /></td>
			<td><fmt:message key="speed" /></td>
			<td><fmt:message key="traffic" /></td>
			<c:if test="${user.role.id != 3}">
				<td><fmt:message key="inactive" /></td>
			</c:if>
		</tr>
		<c:forEach var="tariffs" items="${tariffsList}">
			<c:if test="${tariff.inactive != true}">
				<tr>
					<c:if test="${user.role.id != 3}">
						<td>${tariff.id}</td>
					</c:if>
					<td>${tariff.name}</td>
					<td>${tariff.price}</td>
					<td>${tariff.speed}</td>
					<td><c:choose>
							<c:when test="${{tariff.traffic = 0}">
								<fmt:message key="unlim" />
							</c:when>
							<c:otherwise>
     						  ${tariff.traffic} Mb
  							  </c:otherwise>
						</c:choose></td>
					<c:if test="${user.role.id != 3}">
						<td>${tariff.inactive}</td>
					</c:if>

				</tr>
			</c:if>
		</c:forEach>
	</table>
	<br>
	<br>
	<br>
	<c:if test="${user.role.id = 1}">
									
	<fmt:message key="add_tariff" />
	<br>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="add_tariff" />
		<fmt:message key="tariff_name" />
		:<br /> <input type="text" name="name" value="" /><br />
		<fmt:message key="tariff_price" />
		:<br /> <input type="text" name="price" value="" /><br /> 
		<fmt:message key="tariff_speed" />
		:<br /> <input type="text" name="speed" value="" /><br /> 
		
		<fmt:message key="tariff_traffic" />
		:<br /> <input type="text" name="traffic" value="" /><br /> 
		
		<fmt:message key="tariff_inactive" />
		:<br /> <input type="checkbox" name="inactive" value="true" /><br /> 
		
		<input
			type="submit" value='<fmt:message key="add"/> ' />
	</form>

	<br>
	<c:if test="${not empty addTariffMessage}">
		<fmt:message key="${addTariffMessage}" />
	</c:if>
</c:if>
	
	<%@include file="WEB-INF/jsp_fragments/header.jsp"%>

</body>
</html>



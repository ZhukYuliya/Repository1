<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>NewNet: Tariff editing</title>
</head>
<body>

	<%@include file="/WEB-INF/jsp_fragments/header.jsp"%>

	<h1>
		<fmt:message key="fill_tariff_details" />:
	</h1>
	<br>
	<form action="controller" method="post">
		<table>
			<tr>
				<td><fmt:message key="tariff_name" />:</td>
				<td><input type="text" name="name" value="${tariff.name}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="tariff_price" />, BYN :</td>
				<td><input type="text" name="price" value="${tariff.price}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="tariff_speed" />:</td>
				<td><input type="text" name="speed" value="${tariff.speed}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="tariff_traffic" />:</td>
				<td><input type="text" name="traffic" value="${tariff.traffic}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="tariff_inactive" />:</td>
				<td><select name="inactive">
						<option value="false"><fmt:message key="active" /></option>
						<option value="true"><fmt:message key="inactive" /></option>
				</select></td>
			</tr>
		</table>
		<input type="hidden" name="command" value="save_tariff" />
		<input type="submit" value='<fmt:message key="save_button"/> ' />
	</form>
	<br>
	<c:if test="${not empty saveTariffMessage}">
		<fmt:message key="${saveTariffMessage}" />
	</c:if>
</body>
</html>



<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<title>NewNet: <fmt:message key="tariff_editing" /></title>
</head>
<body>

	<%@include file="/WEB-INF/jspf/header.jspf"%>
<main>
<section id="edit">
	<h1>
		<fmt:message key="fill_tariff_details" />
		:
	</h1>
	<br>
	<form action="${contextPath}/controller" method="post" name="editTariff" 
			onsubmit="return validateEditTariffForm()">
		<table >
			<c:if test="${not empty tariff}">
				<tr>
					<td>ID:</td>
					<td><input type="text" name="id" readonly value="${tariff.id}" /></td>
				</tr>
			</c:if>
			<tr>
				<td><fmt:message key="tariff_name"/> *:</td>
				<td><input type="text" name="name" placeholder="${tariff.name}"
					value="${tariff.name}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="tariff_price"/>, <fmt:message key="currency"/> *:</td>
				<td><input type="text" name="price"
					placeholder="${tariff.price}" value="${tariff.price}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="tariff_speed"/>, <fmt:message
						key="mbps" /> *:</td>
				<td><input type="text" name="speed"
					placeholder="${tariff.speed}" value="${tariff.speed}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="tariff_traffic"/>, <fmt:message
						key="gb" /> *:</td>
				<td><input type="text" name="traffic"
					placeholder="${tariff.traffic}" value="${tariff.traffic}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="tariff_activity" />:<c:choose>
						<c:when test="${tariff.inactive}">
							<fmt:message key="inactive" />
						</c:when>
						<c:when test="${!tariff.inactive}">
							<fmt:message key="active" />
						</c:when>
					</c:choose></td>
				<td><select name="inactive">
						<option value="false"><fmt:message key="active" /></option>
						<option value="true"><fmt:message key="inactive" /></option>
				</select></td>
			</tr>
		</table>
		<input type="hidden" name="command" value="save_tariff" /> <input
			type="submit" value='<fmt:message key="save_button"/> ' />
	</form>
	
	<form action="${contextPath}/controller" method="get">
		<input type="hidden" name="command" value="show_tariffs" /> <input
			type="submit" value='<fmt:message key="back_to_tariffs"/>'/>
	</form>	
	<c:if test="${not empty saveTariffMessage}">
			<span><fmt:message key="${saveTariffMessage}" /></span>
	</c:if>
	</section>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
	</main>
	
</body>
</html>



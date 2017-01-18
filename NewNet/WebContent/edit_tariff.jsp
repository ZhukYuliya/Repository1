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

	<%@include file="WEB-INF/jsp_fragments/header.jsp"%>

	<h1>
		<fmt:message key="edit_tariff" />
	</h1>
	
	<form action="controller" method="post">
		<input name="name" value="${tariff.name}" class="text" /> <input
			name="price" value="${tariff.price}" class="text" /> <input
			name="speed" value="${tariff.speed}" class="text" /> <input
			name="traffic" value="${tariff.traffic}" class="text" /> 
			<select name="inactive">
			<option value="true"><fmt:message key="inactive" /></option>
			<option value="false"><fmt:message key="active" /></option>
			<option value="3"><fmt:message key="customer" /></option>
		</select> 
		
		<input type="hidden" name="command" value="edit_tariff" /> <input
			type="submit" class="submit"
			value='<fmt:message key="change_button"/> ' />
	</form>
</body>
</html>



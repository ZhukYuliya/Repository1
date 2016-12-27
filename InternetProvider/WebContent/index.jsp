<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
   TABLE {
    width: 300px; /* Ширина таблицы */
    border-collapse: collapse; /* Убираем двойные линии между ячейками */
   }
   TD {
    background: #b0e0e6;
    padding: 3px; /* Поля вокруг содержимого таблицы */
    border: 1px solid black; /* Параметры рамки */
   }
  </style>
<title>Title</title>
</head>
<body>

<%@include file="WEB-INF/jsp_fragments/header.jsp" %>


<h1> <fmt:message key="welcome"/></h1>

<br>

	<c:if test="${loginFailed == true}">
		<fmt:message key="loginFailed"/> 
	</c:if>

<br>

	<form action="controller" method="post">
		<input type="hidden" name="command" value="logination" /> 
		<table>
		<tr>
			<td><fmt:message key="login"/>:</td>
			<td><input type="text" name="login" value="" /> </td>
		</tr>
		<tr>
			<td><fmt:message key="password"/>: </td>
			<td><input type="password" name="password" value="" /> </td>
		</tr>
		</table>
		<input type="submit" value= '<fmt:message key="sign_in"/> ' />
	</form>
	
<br>	
<br>
<c:if test="${not empty registrationMessage}"> 
   <fmt:message key="${registrationMessage}"/> </c:if>
   
 
<br>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="registration" /> 
		<table>
		<tr>
			<td><fmt:message key="login"/>:</td>
			<td><input type="text" name="login" value="" /></td>
		</tr>
		<tr>
			<td><fmt:message key="password"/>: </td>
			<td><input type="password" name="password" value="" /> </td>
		</tr>
		<tr>
			<td><fmt:message key="repeatPassword"/>: </td>
			<td><input type="password" name="repeatPassword" value="" /> </td>
		</tr>
		<tr>
			<td><fmt:message key="name"/>: </td>
			<td><input type="text" name="name" value="" /> </td>
		</tr>
		</table>
		<input type="submit" value= '<fmt:message key="register"/> ' />
	</form>
<br>




</body>
</html>


    
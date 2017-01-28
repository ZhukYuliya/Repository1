<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<title>${lastRequestUrl} NewNet: <fmt:message key="new_contract"/></title>
</head>
<body>

	<%@include file="/WEB-INF/jspf/header.jspf"%>
<main>
<section id="edit">

	<h1>
		<fmt:message key="new_user" />
	</h1>
	<form action="${contextPath}/controller" method="post" name="newContract" 
			onsubmit="return validateNewContractForm()">
		<table>
		<tr>
			<td><fmt:message key="first_name" /></td>
			<td><input name="firstName" value="" type="text" class="text" /></td>
		</tr>
		<tr>
			<td><fmt:message key="second_name" /></td>
			<td><input name="secondName" value="" type="text" class="text" /></td>
		</tr>
		<tr>
			<td><fmt:message key="account_number" /></td>
			<td><input name="contract" value="" type="text" class="text" /></td>
		</tr>
		</table>
				<input type="hidden" name="command" value="save_new_contract" />
		
		<input type="submit" class="submit"
			value='<fmt:message key="save_button"/> ' />
	</form>
	<c:if test="${not empty saveContractMessage}">
		<fmt:message key="${saveContractMessage}" />
	</c:if>
	</section>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
	</main>
</body>
</html>



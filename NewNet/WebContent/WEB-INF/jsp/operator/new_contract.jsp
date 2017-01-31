<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<title>NewNet: <fmt:message key="new_contract"/></title>
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
	<span>
	<c:if test="${not empty param.saveContractMessage}">
		<fmt:message key="${param.saveContractMessage}" />
	</c:if>
	</span>
	<form action="${contextPath}/controller" method="get">
		<input type="hidden" name="command" value="show_requests" /> <input
			type="submit" value='<fmt:message key="back_to_requests"/>'/>
	</form>	
	</section>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
	</main>
</body>
</html>



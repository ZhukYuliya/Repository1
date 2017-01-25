<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp_fragments/head_tag.jsp"%>
<title>NewNet: <fmt:message key="home" /></title>
</head>
<body>

	<%@include file="/WEB-INF/jsp_fragments/header.jsp"%>
	<main> <section id="home">
	<h1>
		<fmt:message key="welcome" />
		, ${user.firstName}
	</h1>
	<div class="options">
		<span></span><c:if test="${not empty paymentMessage}">
			<fmt:message key="${paymentMessage}" />
		</c:if></span>

		<form action="${contextPath}/controller" method="get">
			<input type="hidden" name="command" value="show_tariffs" /> <input
				type="submit" value='<fmt:message key="show_tariffs"/> ' />
		</form>

		<form action="${contextPath}/controller" method="get">
			<input type="hidden" name="command" value="to_payment" /> <input
				type="submit" value='<fmt:message key="refill_balance"/> ' />
		</form>

		<form action="${contextPath}/controller" method="get">
			<input type="hidden" name="command" value="change_personal_details" />
			<input type="submit"
				value='<fmt:message key="change_contacts_password"/> ' />
		</form>
	</div>
	</section> <%@include file="/WEB-INF/jsp_fragments/account.jsp"%>
	</main>
	<%@include file="/WEB-INF/jsp_fragments/footer.jsp"%>
</body>
</html>


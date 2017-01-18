<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>NewNet: <fmt:message key="operator"/></title>
</head>
<body>

	<%@include file="WEB-INF/jsp_fragments/header.jsp"%>

	<h1>
		<fmt:message key="welcome" />
		, ${user.firstName}
	</h1>

	<form action="controller" method="get">
		<input type="hidden" name="command" value="show_requests" /> <input
			type="submit" value='<fmt:message key="show_all_requests"/> ' /> <br />
	</form>

	<%@include file="WEB-INF/jsp_fragments/account.jsp"%>

	<%@include file="WEB-INF/jsp_fragments/footer.jsp"%>
</body>
</html>



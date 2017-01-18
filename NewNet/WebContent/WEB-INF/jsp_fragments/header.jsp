<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="shortcut icon" href="resources/img/favicon.png"
	type="image/png">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="resources/css/menu.css" />
</head>
<fmt:setLocale value="${sessionScope.locale}" />

<div >
<form action="controller" method="post">
	<input type="hidden" name="command" value="change_locale" /> 
	<input type="hidden" name="newLocale" value="ru" /> 
	<input type="submit" value='<fmt:message key="localization.ru_button"/>' />
</form>

<form action="controller" method="post">
	<input type="hidden" name="command" value="change_locale" /> 
	<input type="hidden" name="newLocale" value="en" /> 
	<input type="submit" value='<fmt:message key="localization.en_button" /> ' />
</form>

<c:if test="${not empty sessionScope.user}">
	<p><fmt:message key="user_name" /> ${sessionScope.user.firstName} ${sessionScope.user.secondName}</p>
	<form action="controller" method="get">
	<input type="hidden" name="command" value="log_out" /> 
	<input type="submit" value='<fmt:message key="logOut"/> ' />
	</form>
	<a href="index.jsp"><fmt:message key="home"/></a>
</c:if>

</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="l" uri="/WEB-INF/tlds/headertag" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css" />
<c:set var="contextPath" scope="request" value="${pageContext.request.contextPath}"/>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.png"
	type="image/png">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/menu.css" />
</head>
<fmt:setLocale value="${sessionScope.locale}" />


<div >
<form action="${contextPath}/controller" method="post">
	<input type="hidden" name="command" value="change_locale" /> 
	<input type="hidden" name="newLocale" value="ru" /> 
	<input type="submit" value='<fmt:message key="localization.ru_button"/>' />
</form>

<form action="${contextPath}/controller" method="post">
	<input type="hidden" name="command" value="change_locale" /> 
	<input type="hidden" name="newLocale" value="en" /> 
	<input type="submit" value='<fmt:message key="localization.en_button" /> ' />
</form>

<l:headertag/>

</div>
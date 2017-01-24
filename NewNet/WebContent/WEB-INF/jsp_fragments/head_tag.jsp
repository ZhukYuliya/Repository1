<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css" />
<c:set var="contextPath" scope="request" value="${pageContext.request.contextPath}"/>
<c:set var="byn" value="BYN"/>

<fmt:setLocale value="${sessionScope.locale}" />
<link rel="shortcut icon" href="${contextPath}/resources/img/favicon.png" type="image/png">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/menu.css" />

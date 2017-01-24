<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp_fragments/head_tag.jsp"%>
<title>NewNet: <fmt:message key="page_doesnt_exist_message" /></title>
</head>
<body>
	<%@include file="/WEB-INF/jsp_fragments/header.jsp"%>
	<main><fmt:message key="page_doesnt_exist_message" /></main>
	<%@include file="/WEB-INF/jsp_fragments/footer.jsp"%>
</body>
</html>
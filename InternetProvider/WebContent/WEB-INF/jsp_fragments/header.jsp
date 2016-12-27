<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />

<div style="background-color: yellow">
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
	<form action="controller" method="get">
	<input type="hidden" name="command" value="log_out" /> 
	<input type="submit" value='<fmt:message key="logOut"/> ' />
	</form>
	<a href="home.jsp"><fmt:message key="home"/></a>
</c:if>

</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="l" uri="/WEB-INF/tlds/headertag" %>
<header>
<div class = "top">
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
</header>

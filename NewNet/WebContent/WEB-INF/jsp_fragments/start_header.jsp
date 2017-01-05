<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />

<header>
	<nav>
		<ul>
			<li><a href="#about-company"><fmt:message
						key="about_company" /></a></li>
			<li><a href="#advantages"><fmt:message key="advantages" /></a></li>
			<li><a href="#tariffs"><fmt:message key="tariffs" /></a></li>
			<li><a href="#subscribe"><fmt:message key="subscribe" /></a></li>
			<li><a href="#contacts"><fmt:message key="contacts" /></a></li>
			<li><a href="#social"><fmt:message key="social" /></a></li>
		</ul>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="change_locale" /> <input
				type="hidden" name="newLocale" value="ru" /> <input type="submit"
				value='<fmt:message key="localization.ru_button"/>' />
		</form>

		<form action="controller" method="post">
			<input type="hidden" name="command" value="change_locale" /> <input
				type="hidden" name="newLocale" value="en" /> <input type="submit"
				value='<fmt:message key="localization.en_button" /> ' />
		</form>
	</nav>
</header>





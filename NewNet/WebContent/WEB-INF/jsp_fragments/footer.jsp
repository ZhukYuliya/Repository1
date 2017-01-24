<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="n" uri="/WEB-INF/tlds/copyright" %>

<%-- <fmt:setLocale value="${sessionScope.locale}" />
 --%><footer>
	<n:copyright styleClass="copy"/>
	<div class="social" id="social">
		<p>
			<fmt:message key="follow_us" />
		</p>
		<a href="http://twitter.com/newnet"> <img src="resources/img/twitter.jpg"
			alt="Twitter" title="Go to twitter" height="50"></a> <a
			href="http://facebook.com/newnet"> <img src="resources/img/facebook.jpg"
			alt="Facebook" title="Go to facebook" height="50"></a>
	</div>
</footer>
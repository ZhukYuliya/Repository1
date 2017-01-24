<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <fmt:setLocale value="${sessionScope.locale}" />
 --%>
<p>
	<fmt:message key="account_info" />
</p>

<table border="1">
	<tr>
		<td><fmt:message key="account_number"/></td>
		<td><fmt:message key="account_balance"/>, BYN</td>
		<td><fmt:message key="current_tariff"/></td>
		<td><fmt:message key="blocked"/></td>
	</tr>
	<tr>
		<td>${user.account}</td>
		<td>${user.accountBalance}</td>
		<td>${user.tariff.name}</td>
		<td><c:choose>
				<c:when test="${user.blocked}">
					<fmt:message key="${blocked}" />
				</c:when>
				<c:otherwise>
					<fmt:message key="${not_blocked}" />
				</c:otherwise>
			</c:choose></td>
	</tr>
</table>

<c:if test="${not empty subscriptionMessage}">
	<fmt:message key="successfull_subscription" />
</c:if>



<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <fmt:setLocale value="${sessionScope.locale}" />
 --%>
 <section id="account">
<span>
	<fmt:message key="account_info" />
</span>

<table>
	<tr>
		<th><fmt:message key="account_number"/></th>
		<th><fmt:message key="account_balance"/>, BYN</th>
		<th><fmt:message key="current_tariff"/></th>
		<th><fmt:message key="blocked"/></th>
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
<span>
<c:if test="${not empty subscriptionMessage}">
	<fmt:message key="successfull_subscription" />
</c:if>
</span>
</section>

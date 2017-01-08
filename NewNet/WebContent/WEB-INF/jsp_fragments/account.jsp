<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setLocale value="${sessionScope.locale}" />

<p><fmt:message key="account_info" /></p>
	
	<table border="1">
		<tr>
			<td><fmt:message key="account_number" /></td>
			<td><fmt:message key="account_balance" /></td>
			<td><fmt:message key="current_tariff" /></td>
			<td><fmt:message key="is_blocked" /></td>
		</tr>
		<tr>
			<td>${user.account.number}</td>
			<td>${user.account.balance}</td>
			<td>${user.account.tariff.name}</td>
			<td>${user.account.blocked}</td>
		</tr>
	</table>


	<form action="controller" method="get">
		<input type="hidden" name="command" value="subsribe_tariff" /> 
		<input type="submit" value='<fmt:message key="subsribe_for_tariff"/> ' /> <br />
		<select name="newTariff">
			<option><fmt:message key="choose_tariff" /></option>
			<c:forEach var="tariff" items="${tariffs}">
        		<option value="${tariff.id}"> ${tariff.name}</option>
     		 </c:forEach>
		</select>
		</form>
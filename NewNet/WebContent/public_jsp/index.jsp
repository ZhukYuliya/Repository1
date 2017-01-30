<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/jspf/head_tag.jspf"%>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<title>NewNet: <fmt:message key="internet_provider" /></title>
</head>
<body>
	<header> <%@include file="/WEB-INF/jspf/header.jspf"%>
	<%@include file="/WEB-INF/jspf/menu.jspf"%>
	</header>
	<main> 	<c:if test="${empty user}">
	<section id="wrappertop">
	<div class="wrapper">
		<div class="logo">
			<a href="http://newnet.by/" title="Логотип"> <img
				src="${contextPath}/resources/img/logo.png" alt="NewNet" /></a>
		</div>
		<div class="login">
			<div class="subtitle">
				<fmt:message key="sign_in_message" />
			</div>
			<form action="${contextPath}/controller" method="post" name="authentication" 
			onsubmit="return validateAuthenticationForm()">
				<input name="account" value=""
					placeholder='<fmt:message key="contract_number"/>' class="text" />
				<input name="password" type="password" value=""
					placeholder='<fmt:message key="password"/>' class="text" /> <input
					type="hidden" name="command" value="authentication" /> <input
					type="submit" class="submit"
					value='<fmt:message key="sign_in_button"/> ' /> <br>
				<c:if test="${not empty authenticationMessage}">
					<fmt:message key="${authenticationMessage}" />
				</c:if>
						<c:if test="${not empty postRequestMessage}">
				<fmt:message key="${postRequestMessage}" />
			</c:if>
			</form>
		</div>
		<div class="register">
			<div class="subtitle">
				<fmt:message key="register" />
			</div>
			<form action="${contextPath}/controller" method="post" name="checkAccount" 
			onsubmit="return validateCheckAccountForm()">
				<input name="account" value=""
					placeholder='<fmt:message key="contract_number"/>' class="text" />
				<input type="hidden" name="command" value="check_account" /> <input
					type="submit" class="submit"
					value='<fmt:message key="register_button"/> ' /> <br>
				<c:if test="${not empty checkAccountMessage}">
					<fmt:message key="${checkAccountMessage}" />
				</c:if>
				<c:if test="${not empty param.registrationMessage}">
					<fmt:message key="${param.registrationMessage}" />
				</c:if>
			</form>
		</div>
		<div class="slogan">
			<fmt:message key="slogan" />
			<br /> <span class="small"><fmt:message key="slogan_small" /></span>
		</div>
	</div>
	</section></c:if> <a name="about-company"></a> <section id="internettop">
	<div class="wrapper">
		<div class="img">
			<img src="${contextPath}/resources/img/globe.jpg" />
		</div>
		<div class="text">
			<p>
				<strong><fmt:message key="about_company_title" /></strong>
			</p>
			<p>
				<fmt:message key="about_company_subtitle" />
			</p>
			<p>
				<fmt:message key="about_company_text" />
			</p>
		</div>

		<div class="clearfix"></div>
	</div>
	</section> <a name="advantages"></a> <section id="advantages">
	<div class="wrapper">
		<div class="top_title">
			<fmt:message key="advantages" />
		</div>
		<div class="text">
			<div class="img">
				<img src="${contextPath}/resources/img/advantages.png" />
			</div>
			<table>
				<tr>
					<td width="25%"><fmt:message key="advantage1" /></td>
					<td width="25%"><fmt:message key="advantage2" /></td>
					<td width="25%"><fmt:message key="advantage3" /></td>
					<td width="25%"><fmt:message key="advantage4" /></td>
				</tr>
			</table>
		</div>
	</div>
	</section> <a name="order"></a><section id="order">
	<div class="wrapper">
		<div class="top_title">
			<fmt:message key="place_order" />
		</div>
		<div class="small">
			<fmt:message key="call_for_details" />
		</div>
		<form action="${contextPath}/controller" method="post" name="request" 
			onsubmit="return validateRequestForm()">
			<input name="name" value=""
				placeholder="<fmt:message key="your_name"/> *" class="text" /> <input
				name="email" value="" placeholder="<fmt:message key="your_email"/> *"
				class="text" /> <input name="phone" value=""
				placeholder="<fmt:message key='your_phone'/> 375XXXXXXXXX *" class="text" /> <input
				name="address" value=""
				placeholder="<fmt:message key="your_address"/> *" class="text" /> <input
				type="hidden" name="command" value="post_request" /> <input
				type="submit" class="submit"
				value='<fmt:message key="post_request_button"/> ' />
		</form>
	</div>
	</section> <a name="tariffs"></a> <section id="tariffs">
	<div class="wrapper">
		<div class="top_title">
			<fmt:message key="tariffs"/>
		</div>
		<div class="item">
			<div class="title">
				<fmt:message key="unlim_tariffs"/>
			</div>
			<div class="price">
				<span class="num"><fmt:message key="from"/> 12</span><br /> <span
					class="small"><fmt:message key="currency"/>/<fmt:message key="month"/></span>
			</div>
			<div class="descr">
				<fmt:message key="speed_up_to" />
				100
				<fmt:message key="mbps" />
			</div>
		</div>
		<div class="item">
			<div class="title">
				<fmt:message key="limit_tariffs" />
			</div>
			<div class="price">
				<span class="num"><fmt:message key="from" /> 7.99</span><br /> <span
					class="small"><fmt:message key="currency"/>/<fmt:message key="month" /></span>
			</div>
			<div class="descr">
				<fmt:message key="variants" />
			</div>
		</div>
		<div class="item">
			<div class="title">
				<fmt:message key="for_business" />
			</div>
			<div class="price">
				<span class="num"><fmt:message key="from" /> 20</span><br /> <span
					class="small"><fmt:message key="currency"/>/<fmt:message key="month" /></span>
			</div>
			<div class="descr">
				<fmt:message key="discounts_for_business" />
			</div>
		</div>
		<a href="${contextPath}/controller?command=show_tariffs"></br>
		<fmt:message key="click_for_tariffs" /> </a>
	</div>
	</section> <a name="subscribe"></a> <section id="scheme">
	<div class="wrapper">
		<div class="top_title">
			<fmt:message key="procedure" />
		</div>
		<div class="text">
			<div class="img">
				<img src="${contextPath}/resources/img/scheme.png" />
			</div>
			<table>
				<tr>
					<td width="25%"><fmt:message key="procedure_step1" /></td>
					<td width="25%"><fmt:message key="procedure_step2" /></td>
					<td width="25%"><fmt:message key="procedure_step3" /></td>
					<td width="25%"><fmt:message key="procedure_step4" /></td>
				</tr>
			</table>
		</div>
	</div>
	</section> <a name="contacts"></a> <section id="contacts" class="map">
	<div class="wrapper">
		<div class="img">
			<img src="${contextPath}/resources/img/map.png" />
		</div>
		<div class="block">
			<p>
				<strong><fmt:message key="our_address" /></strong><br />
				<fmt:message key="minsk_address" />
			</p>
			<p>
				<strong> <fmt:message key="call_us" /></strong><br /> +375 (29)
				222-22-22
			</p>
			<p>
				<strong><fmt:message key="email_us" /></strong><br />
				info@newnet.by
			</p>
		</div>
		<div class="clearfix"></div>
	</div>
	</section> </main>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
	
</body>
</html>





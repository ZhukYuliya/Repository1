<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="shortcut icon" href="resources/img/favicon.png"
	type="image/png">
<title>NewNet: <fmt:message key="internet_provider" /></title>

<!-- CSS -->
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link rel="stylesheet" type="text/css" href="resources/css/menu.css" />
</head>
<body>
	<%@include file="WEB-INF/jsp_fragments/start_header.jsp"%>

	<main> <section id="wrappertop">
	<div class="wrapper">
		<div class="logo">
			<a href="http://newnet.by/" title="Логотип"> <img
				src="resources/img/logo.png" alt="NewNet" /></a>
		</div>
		<div class="login">
			<div class="subtitle">
				<fmt:message key="sign_in_message" />
			</div>
			<form action="controller" method="post">
				<input name="account" value=""
					placeholder='<fmt:message key="contract_number"/>' class="text" />
				<input name="password" type="password" value=""
					placeholder='<fmt:message key="password"/>' class="text" /> <input
					type="hidden" name="command" value="authentication" /> <input
					type="submit" class="submit"
					value='<fmt:message key="sign_in_button"/> ' /> <br>
				<c:if test="${not empty authenticationMessage}">
					<fmt:message key="${authentication_message}" />
				</c:if>
				<c:if test="${loginFailed == true}">
					<fmt:message key="loginFailed" />
				</c:if>
			</form>
		</div>
		<div class="register">
			<div class="subtitle">
				<fmt:message key="register" />
			</div>
			<form action="controller" method="post">
				<input name="account" value=""
					placeholder='<fmt:message key="contract_number"/>' class="text" />
				<input type="hidden" name="command" value="check_account" /> <input
					type="submit" class="submit"
					value='<fmt:message key="register_button"/> ' /> <br>
				<c:if test="${not empty checkAccountMessage}">
					<fmt:message key="${checkAccountMessage}" />
				</c:if>
				<c:if test="${not empty registrationMessage}">
					<fmt:message key="${registrationMessage}" />
				</c:if>
			</form>
		</div>
		<div class="slogan">
			<fmt:message key="slogan" />
			<br /> <span class="small"><fmt:message key="slogan_small" /></span>
		</div>
	</div>
	</section> <a name="about-company"></a> <section id="internettop">
	<div class="wrapper">
		<div class="img">
			<img src="resources/img/globe.jpg" />
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
				<img src="resources/img/advantages.png" />
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
	</section> <section id="order">
	<div class="wrapper">
		<div class="contact">
			<a class="phone" href="tel:+375(29) 222-22-22">(29) 222-22-22</a> <a
				class="phone" href="tel:+375(29) 444-44-44">(29) 444-44-44</a>
			<div class="text">
				<fmt:message key="call_message" />
			</div>
			<div class="top_title">
				Оставьте заявку на подключение<br />к сети Интернет с NewNet
			</div>
			<div class="small">Наш специалист свяжется с вами в течение 5
				минут для уточнения деталей</div>
			<form method="post">
				<input name="name" value="" placeholder="Ваше имя" class="text" />
				<input name="email" value="" placeholder="Ваша почта" class="text" />
				<input name="phone" value="" placeholder="Ваш телефон" class="text" />
				<input name="address" value="" placeholder="Адрес подключения"
					class="text" /> <input type="hidden" name="command"
					value="post_request" /> <input type="submit" class="submit"
					value='<fmt:message key="post_request_button"/> ' />
			</form>
		</div>
	</section> <a name="tariffs"></a> <section id="tariffs">
	<div class="wrapper">
		<div class="top_title">Тарифные планы</div>
		<div class="item">
			<div class="title">Телефония</div>
			<div class="price">
				<span class="num">от 3</span><br /> <span class="small">BYN/месяц</span>
			</div>
			<div class="descr">Городские телефонные номера.</div>
		</div>
		<div class="item">
			<div class="title">Интернет</div>
			<div class="price">
				<span class="num">от 5</span><br /> <span class="small">BYN/месяц</span>
			</div>
			<div class="descr">
				Скорость доступа до <br />100 Мбит/c
			</div>
		</div>
		<div class="item">
			<div class="title">Телевидение</div>
			<div class="price">
				<span class="num">от 5,5</span><br /> <span class="small">BYN/месяц</span>
			</div>
			<div class="descr">Более 150 каналов</div>
		</div>
		<a href="/alltariffs"></br>Кликните для просмотра всех тарифов </a>
	</div>
	</section> <a name="subscribe"></a> <section id="scheme">
	<div class="wrapper">
		<div class="top_title">
			<fmt:message key="procedure" />
		</div>
		<div class="text">
			<div class="img">
				<img src="resources/img/scheme.png" />
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
			<img src="resources/img/map.png" />
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
</body>
</html>




<%@include file="WEB-INF/jsp_fragments/footer.jsp"%>

</body>
</html>



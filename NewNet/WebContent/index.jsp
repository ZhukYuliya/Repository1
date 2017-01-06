<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css" />
<title>NewNet – лучший интернет провайдер.</title>

<!-- CSS -->
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css" />
</head>
<body>
	<%@include file="WEB-INF/jsp_fragments/start_header.jsp"%>

	<main><section id="wrappertop">
	<div class="wrapper">
		<div class="logo">
			<a href="http://newnet.by/" title="Логотип"> <img
				src="img/logo.png" alt="NewNet" /></a>
		</div>
		<div class="contact">
			<a class="phone" href="tel:+375(29) 222-22-22">(29) 222-22-22</a> <a
				class="phone" href="tel:+375(29) 444-44-44">(29) 444-44-44</a>
			<div class="text">
				<fmt:message key="call_message" />
			</div>
		</div>
		<div class="login">
			<div class="subtitle">
				<fmt:message key="sign_in_message" />
			</div>
			<form action="controller" method="post">
				<input name="login" value=""
					placeholder='<fmt:message key="login"/>' class="text" /> <input
					name="password" value=""
					placeholder='<fmt:message key="password"/>' class="text" /> <input
					type="hidden" name="command" value="authentication" /> <input
					type="submit" class="submit"
					value='<fmt:message key="sign_in_button"/> ' /> <br>

				<c:if test="${loginFailed == true}">
					<fmt:message key="loginFailed" />
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
			<img src="img/globe.jpg" />
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
				<img src="img/advantages.png" />
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
		<div class="top_title">
			Оставьте заявку на подключение<br />к сети Интернет с NewNet
		</div>
		<div class="small">Наш специалист свяжется с вами в течение 5
			минут для уточнения деталей</div>
		<form method="post">
			<input name="name" value="" placeholder="Ваше имя"
				class="text" /> <input name="email" value=""
				placeholder="Ваша почта" class="text" /> <input name="phone"
				value="" placeholder="Ваш телефон" class="text" /> <input
				name="address" value=""
				placeholder="Адрес подключения" class="text" /> <input
				type="hidden" name="command" value="post_request" /> <input
				type="submit" class="submit" value='<fmt:message key="post_request_button"/> ' />
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
		<div class="top_title">Схема подключения</div>
		<div class="text">
			<div class="img">
				<img src="img/scheme.png" />
			</div>
			<table>
				<tr>
					<td width="25%">Вы оставляете заявку на нашем сайте или по
						телефону и мы подбираем для Вас тариф</td>
					<td width="25%">Мы приезжаем <br />и подключаем Вас<br />к
						сети Интернет
					</td>
					<td width="25%">В течение 1 недели Вы<br />пользуетесь
						услугами<br />бесплатно в тестовом режиме
					</td>
					<td width="25%">Мы подписываем договор<br />на оказание услуг
					</td>
				</tr>
			</table>
		</div>
	</div>
	</section> <a name="contacts"></a> <section id="contacts" class="map">
	<div class="wrapper">
		<div class="img">
			<img src="img/map.png" />
		</div>
		<div class="block">
			<p>
				<strong>Мы находимся по адресу:</strong><br /> 220019, Минск, <br />Революционная
				ул., д.17, 3 этаж
			</p>
			<p>
				<strong>Позвоните нам</strong><br /> +375 (29) 222-22-22
			</p>
			<p>
				<strong>Отправьте сообщение</strong><br /> <span class="email">info@newnet.by</span>
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



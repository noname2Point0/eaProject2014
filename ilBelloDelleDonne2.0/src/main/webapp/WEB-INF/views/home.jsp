<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="it">
<head>
<meta charset="utf-8">
<title>il bello delle donne</title>
<link href="resources/styles/bdd.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/scripts/jquery-1.11.1.js"></script>

<script type="text/javascript">
	$(function() {

		$("#footer").load("resources/template/footer.html");
		$("#header").load("resources/template/header.html");

	});
</script>

</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigationBar">
			<ul>
				<li><a href="home">home</a></li>
				<li><a href="chiSiamo">chi siamo</a></li>
				<li><a href="products">prodotti</a></li>
				<li><a href="services">servizi</a></li>
				<li></li>
				<li></li>
				<c:if test="${empty user}">
					<li><a href="login">login</a></li>
				</c:if>
				<c:if test="${!empty user}">
					<li><a href="logout">logout</a></li>
				</c:if>
				<li><a href="myAccount">my account</a></li>
				<li><a href="shoppingCart">carrello</a></li>
			</ul>
			<hr />
		</div>

		<div id="content">
			<div class="imgContainer">
				<img src="resources/images/tagli.png" />
			</div>
			<p class="homeP">
				il bello delle donne: Tel. 3408432624<br>e-mail:
				ilbellodelledonne@coiffeur.it<br>C.so Mazzini,11<br>
				Cosenza<br>
			</p>
			<br> <br>
			<p class="homeP">
				Orario:<br>Martedi': 8:00 - 19:00<br>Mercoledi': 8:00 -
				19:00<br>Giovedi': 7:00 - 20:00<br>Venerdi': 7:00 - 20:00<br>Sabato:
				7:00 - 20:00<br>
			</p>
			<br> <br>
			<p class="homeP">Prenota on-line -10%</p>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>

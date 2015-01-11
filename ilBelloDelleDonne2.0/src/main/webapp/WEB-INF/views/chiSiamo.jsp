<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="it">
<head>
<meta charset="utf-8">
<title>il bello delle donne</title>
<link href="resources/styles/bdd.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/scripts/jquery-1.11.1.js">
	
</script>

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
				<img src="resources/images/salone.png" /> <br> <img
					src="resources/images/taglio.png" />
			</div>
			<p class="homeP">
				Passione esperienza e cortesia al servizio del vostro benessere e
				della vostra bellezza.<br> Il bello delle donne, con i migliori
				prodotti e i migliori professionisti cerca di soddisfare ogni tua
				esigenza!<br>
			</p>
			<br> <br>
			<p class="homeP">il salone, situato nel corso piu' affascinante
				dell'antica citta' dei bruzi, e' caratterizzato da un arredamento
				sobrio ed elegante, che si integra splendidamente tra gli storici
				edifici del corso cosentino.</p>
			<br> <br>
			<p class="homeP">E' infatti una passione ventennale quella che
				accumuna tutti i discendenti della famiglia Pane, che dal 1970 sono
				stati punti di eccelso nell'acconciature femminili nell'interland
				cosentino</p>
			<br> <br>
			<p class="homeP">Cosa aspetti allora? vieni a trovarci, per te
				innumerevoli servizi.</p>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>

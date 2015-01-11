<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="it">
<head>
<meta charset="utf-8">
<title>il bello delle donne</title>
<link href="resources/styles/bdd.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/scripts/jquery-1.11.1.js"></script>
<script type="text/javascript" src="resources/scripts/fields.js"></script>

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
			<c:if test="${!empty message}">
				<p>
					<c:out value="${message}" />
				</p>
				<br>
				<br>
			</c:if>
			<div class="signIn">
				<h3>Sign In</h3>
				<hr />
				<form id="mod" method="post" name="modulo" action="signIn">
					<h4>Anagrafica</h4>
					<p>
						<input type="text" value="" placeholder="nome *" name="nome">
						<input type="text" value="" placeholder="cognome *" name="cognome">
					</p>
					<p>
						<input type="text" value="" placeholder="citta' di residenza"
							name="cittaR"> <input type="text" value=""
							placeholder="via di residenza" name="viaR">
					</p>
					<p>
						<input type="text" value="" placeholder="recapito telefonico"
							name="recTelefono"> <input type="date" value=""
							placeholder="data di nascita" name="dataNascita">
					</p>
					<p>
						<input type="text" value="" placeholder="cf / P_Iva"
							name="pIva_cf">
					</p>
					<br>
					<hr />
					<h4>Account info</h4>
					<p>
						<input type="text" value="" placeholder="e-mail" name="email">
					</p>
					<p>
						<input type="text" value="" placeholder="username"
							name="usernameS"> <input type="password" value=""
							placeholder="password" name="passwordS">
					</p>
					<p id="pshifted">
						<input type="password" value=""
							placeholder="reinserisci la password" name="passwordSR">
					</p>
					<!--  	<p>
						<input type="text" value=""
							placeholder="inserisi una domanda segreta" name="dmndSecret">
						<input type="text" value="" placeholder="inserisci una risposta"
							name="rispSecret">
					</p> -->
					<p>
						<input type="submit" name="commitS" value="Sign In">
					</p>
				</form>
			</div>

		</div>

		<div id="footer"></div>
	</div>
</body>
</html>
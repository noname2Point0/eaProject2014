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
		<p>la tua prenotazione è andata a buon fine.<br>Puoi vedere il resto delle tue prenotazioni nella pagina a te riservata</p>
		<p>ricapitolo</p>
		
					<table class="mytable">
						<thead>
							<tr>
								<th>servizio</th>
								<th>data di prenotazione</th>
								<th>data prenotata</th>
								<th>ora prenotata</th>
								<th>nome</th>
								<th>cognome</th>
								<th>prezzo</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${reserve.service.description}</td>
								<td>${reserve.dateOrder}</td>
								<td>${reserve.dateService}</td>
								<td>${reserve.time}</td>
								<td>${reserve.customer.name }</td>
								<td>${reserve.customer.surname }</td>
								<td>${reserve.service.price}</td>
								<td></td>
							</tr>
						</tbody>
					</table>
		</div>

	</div>
		<div id="footer"></div>
</body>
</html>

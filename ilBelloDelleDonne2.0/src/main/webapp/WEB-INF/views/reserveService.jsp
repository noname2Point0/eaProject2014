<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="it">
<head>
<meta charset="utf-8">
<title>il bello delle donne</title>
<link href="resources/styles/bdd.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/scripts/jquery-1.11.1.js">
	<script type="text/javascript">
	$(function() {

		$("#footer").load("resources/template/footer.html");
		$("#header").load("resources/template/header.html");

	});
</script>

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
			<p>conferma la tua prenotazione</p>
			<br>
			<p>servizio scelto</p>
			<br>
			<table class="mytable">
				<thead>
					<tr>
						<th>description</th>
						<th>price</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${service.description}</td>
						<td>${service.price}</td>
					</tr>
				</tbody>
			</table>
			<br>
			<p>seleziona la data e l'ora per la tua prenotazione</p>
			<br>
			<table class="mytable">
				<thead>
					<tr>
						<th>data</th>
						<th>ora</th>
						<th>conferma</th>
					</tr>
				</thead>
				<tbody>
				
					<form:form action="confirmReserve" method="post" modelattribute="service">
						<tr>
							<td><input type="date" name="data" min="${dateMin}"></td>
							<td><input type="time" name="time"></td>
							<td> 
							   <input type="hidden" name="id" value="${service.id}">
								<input type="hidden" name="description" value="${service.description}"> 
								<input type="hidden"  name="price" value="${service.price}">
								<input type="submit" value="conferma"></td>
						</tr>
					</form:form>
				</tbody>
			</table>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>

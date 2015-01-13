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
		<p>il tuo acquisto � andato a buon fine.<br>
		
		<p>ricapitolo</p>
		
					<table class="mytable">
						<thead>
							<tr>
								<th>id</th>
								<th>date order</th>
								<th>date send</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${selling.id}</td>
								<td>${selling.dateOrder}</td>
								<td>${selling.dateConsignment}</td>
							</tr>
						</tbody>
					</table>
				
				<br><br>
				<p>products</p>
				<table class="mytable">
				<thead>
					<tr>
						<th>type</th>
						<th>brand</th>
						<th>description</th>
						<th>price</th>
						<th>quantity</th>
						<th>tot</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stock" items="${stockList}">
						<tr>
							<td>${stock.type}</td>
							<td>${stock.brand}</td>
							<td>${stock.description}</td>
							<td>${stock.price}</td>
							<td>${stock.quantity}</td>
							<td><c:out value="${stock.quantity}* ${stock.price}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>

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

		<div id="tcontent">
			<p>Services</p>
			<br> <br>
			<table class="mytable">
				<thead>
					<tr>
						<th>description</th>
						<th>price</th>
						<th>click</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="service" items="${serviceList}">
						<tr>
							<td>${service.description}</td>
							<td>${service.price}</td>
							<td>
								<form action="reserveService" method="post">
									<input type="submit" value="reserve"> <input
										type="hidden" name="id" value="${service.id}"> <input
										type="hidden" name="description"
										value="${service.description }"> <input type="hidden"
										name="price" value="${service.price }">
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br> <br>
			<p>*lo shampoo viene effettuato per ognuno di questi servizi</p>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>

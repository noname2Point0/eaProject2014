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

		<div id="tcontent">
			<p>Products</p>
			<br>

			<table class="mytable">
				<thead>
					<tr>
						<th>type</th>
						<th>brand</th>
						<th>description</th>
						<th>price</th>
						<c:if test="${user.account.type =='customer' || empty user.account.type }">
						<th>add to cart</th> 
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stock" items="${stockList}">
						<tr>
							<td>${stock.type}</td>
							<td>${stock.brand}</td>
							<td>${stock.description}</td>
							<td>${stock.price}</td>
							<c:if test="${user.account.type =='customer' || empty user.account.type }">
							<td>
								<form action="addToCart" method="get" modelattribute="productCustom">
								<input type="submit" value="add"> 
								<input type="hidden" name="type" value="${stock.type}"> 
								<input type="hidden" name="brand" value="${stock.brand}"> 
								<input type="hidden" name="description" value="${stock.description}">
								<input type="hidden" name="price" value="${stock.price}">
								<input type="hidden" name="quantity" value="${stock.quantity}">
								</form>
							</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>
			<br>
			<form action="shoppingCart" method="get">
				<input type="submit" value="show cart">
			</form>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>

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

		<div id="tcontent">
			<p>il tuo carrello</p>
			<c:choose>
				<c:when test="${!empty message}">
					<p>
						<c:out value="${message}"></c:out>
					</p>
				</c:when>
				<c:otherwise>
					<table class="mytable">
						<thead>
							<tr>
								<th>type</th>
								<th>brand</th>
								<th>description</th>
								<th>price</th>
								<th>remove</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="productCustom" items="${shoppingList}">
								<tr>
									<td>${productCustom.type}</td>
									<td>${productCustom.brand}</td>
									<td>${productCustom.description}</td>
									<td>${productCustom.price}</td>
									<td>
										<form action="removeToCart" method="get">
											<input type="submit" value="remove"> <input
												type="hidden" name="type" value="${productCustom.type}">
											<input type="hidden" name="brand"
												value="${productCustom.brand}"> <input type="hidden"
												name="description" value="${productCustom.description}">
											<input type="hidden" name="price"
												value="${productCustom.price}">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
			<br>
			<br>
			<form action="sellingProducts" method="get">
				<input type="submit" value="ordina">
			</form>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>

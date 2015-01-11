<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
			<p>conferma il tuo ordine d'acquisto</p>
			<p>seleziona la quantita' per ogni prodotto e clicca su acquista</p>
			<br>
			<p>your products:</p>
			
			<form:form method="get" action="confirmSelling"  modelattribute="productCustomList">
						<c:forEach items="${productCustomList.productsCustom}"  var="product" varStatus="status" >
						
						<table class="mytable">
						<thead>
							<tr>
								<th>type</th>
								<th>brand</th>
								<th>description</th>
								<th>price</th>
								<th>select quantity</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${product.type}</td>
								<td>${product.brand}</td>
								<td>${product.description}</td>
								<td>${product.price}</td>
								<td>
								<input type="number" name="productsCustom[${status.index}].quantity" value="1" min="1"
									max="${product.quantity}"> 
									<input type="hidden" name="productsCustom[${status.index}].type" value="${product.type}">
									<input type="hidden" name="productsCustom[${status.index}].brand" value="${product.brand}"> 
									<input type="hidden" name="productsCustom[${status.index}].description" value="${product.description}">
								</td>
							</tr>
						</tbody>
					</table>
					<br>
					<hr />
					<br>
				</c:forEach>		
				<input type="submit" value="buy" >
			</form:form>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>

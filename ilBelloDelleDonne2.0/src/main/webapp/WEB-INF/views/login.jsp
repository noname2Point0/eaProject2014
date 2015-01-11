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
			<c:if test="${!empty message}">
				<p>
					<c:out value="${message}" />
				</p>
				<br>
				<br>
			</c:if>
			<script src="resources/scripts/login.js" />
			</script>

			<div class="login">
				<h3>login</h3>
				<hr />
				<form id="logForm" method="post" action="login">
					<p>
						<input type="text" value="" placeholder="username" name="username">
					</p>
					<p>
						<input type="password" value="" placeholder="password"
							name="password">
					</p>
					<c:if test="${!empty before}">
						<input type="hidden" name="after" value="${before}">
						<c:if test="${!empty service}">
							<input type="hidden" name="service" value="${service}">
						</c:if>
					</c:if>
					<p>
						<input type="submit" value="login">
					</p>
				</form>
			</div>
			<p class=" pLogHelp">
				<a href="signIn">registrati</a>
			</p>
			<br>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>

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
		
		$("#divview").load("resources/template/viewAccounts.html");
		
	});
</script>

</head>
<body>
	<div id="container">

		<div id="header">
			<c:if test="${empty user}">
				<p>effettua il login per poter visualizzare il tuo account.</p>
			</c:if>
			<c:if test="${!empty user}">
				<p>il tuo account.</p>
			</c:if>
			<hr />
		</div>

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

		<div id="accountContent">
		
			<c:if test="${!empty user}">
				<div id="divmenu">
				<div id="divUserInfo">
						<p>informazioni utente:</p>
						<hr />
							nome:
							<c:out value="${user.name}"></c:out>
							<br> cognome:
							<c:out value="${user.surname}"></c:out>
							<br> username:
							<c:out value="${user.account.username}"></c:out>
							<br> email:
							<c:out value="${user.email}"></c:out>
							<br> ruolo:
							<c:out value="${user.account.type}"></c:out>
							<br>
				</div>
				<div id="divUserMenu">
						<p>menu:</p>
						<hr />
						account:
							<ul class='userMenu'>
								<li><a href='#'>visualizza</a></li>
								<li><a href='#'>modifica</a></li>
								<li><a href='#'>inserisci</a></li>
								<li><a href='#'>elimina</a></li>
							</ul>
						<hr/>
						appuntamenti:						
							<ul class="userMenu">
								<li><a href='#'>visualizza</a></li>
								<li><a href='#'>modifica</a></li>
								<li><a href='#'>inserisci</a></li>
								<li><a href='#'>elimina</a></li>
								<li><a href='#'>check</a></li>
							</ul>
							<hr/>
							ordini:
							<ul class="userMenu">
								<li><a href='#'>visualizza</a></li>
								<li><a href='#'>modifica</a></li>
								<li><a href='#'>inserisci</a></li>
								<li><a href='#'>elimina</a></li>
								<li><a href='#'>check</a></li>
							</ul>
							<hr/>
							magazzino:
							<ul class="userMenu">
								<li><a href='#'>visualizza</a></li>
							</ul>
							<hr/>
							
							statistiche:
							<ul class="userMenu">
								<li><a href='#'>visualizza</a></li>
							</ul>
							</div>
				</div>
				<div id="divview"></div>
			</c:if>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>

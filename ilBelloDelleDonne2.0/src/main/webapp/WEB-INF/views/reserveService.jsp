<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="it">
<head>
<meta charset="utf-8">
<title>il bello delle donne</title>
<link href="resources/styles/bdd.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="resources/scripts/reserve.js"></script>
<script type="text/javascript" src="resources/scripts/jquery-1.11.1.js"></script>

<script type="text/javascript">
<script type="text/javascript" src="resources/scripts/jquery-1.11.1.js"></script>

<script type="text/javascript">
	$(function() {

		$("#footer").load("resources/template/footer.html");
		$("#header").load("resources/template/header.html");

	});
</script>
<script type="text/javascript">
	function reserveData() {
		alert("sono nella function");

		if(document.mod.data.value == "undefined"){
			alert("Inserire una data");			
			document.mod.data.focus();
			return false;
		}
		var data = document.mod.data.value;
		
		var splitData = data.split("-");
		var s1 = document.mod.data.value.substring(1);
		var s2 = document.mod.data.value.substring(2);
		var s3 = document.mod.data.value.substring(3);
		var s4 = document.mod.data.value.substring(4);

		var year = parseInt(splitData[0]);
		var month = parseInt(splitData[1]);
		var day = parseInt(splitData[2]);

		var d = new Date();

		var curr_day = d.getDate();
		if (curr_day == 0) {
			curr_day = curr_day + 1;
		}
		var curr_month = d.getMonth();

		if (curr_month == 0) {
			curr_month = curr_month + 1;
		}

		var curr_year = d.getFullYear();

		if (year < curr_year) {
			alert("Inserire una data corretta.\n L'anno selezionato è inferiore a quello corrente");
			document.mod.data.focus();
			return false;
		}
		else if (year == curr_year && month < curr_month) {
			alert("Inserire una data corretta.\n Il mese selezionato è inferiore a quello corrente");
			document.mod.data.focus();
			return false;
		}
		else if (year == curr_year && month == curr_month && day < curr_day) {
			alert("Inserire una data corretta.\n Il giorno selezionato è inferiore a quello crescente");
			document.mod.data.focus();
			return false;
		}
		else{

			return true;
		}
	}
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

					<form:form id="reserve" action="confirmReserve" method="post"
						name="mod" modelattribute="service">
						<tr>
							<td><input type="date" value="" name="data" min="${dateMin}"></td>
							<td><input type="time" name="time" min="08:00:00" max="19:00:00"></td>
							<td><input type="hidden" name="id" value="${service.id}">
								<input type="hidden" name="description"
								value="${service.description}"> <input type="hidden"
								name="price" value="${service.price}"> <input
								type="submit" onClick="return reserveData();" value="conferma"></td>
						</tr>
					</form:form>
				</tbody>
			</table>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>

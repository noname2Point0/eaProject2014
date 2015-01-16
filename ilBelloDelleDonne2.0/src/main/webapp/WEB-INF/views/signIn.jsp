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
<script type="text/javascript" src="resources/scripts/comuni.js"></script>

<script type="text/javascript">
	$(function() {

		$("#footer").load("resources/template/footer.html");
		$("#header").load("resources/template/header.html");
		
		var date = new Date();
		var dateFormatted = date.getFullYear()+"-"+("0"+(date.getMonth()+1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2);

		$("#dateBirth").attr("value",dateFormatted);
		
		$("#mod").submit(function(){
			var usr =  $("#usr").val();
			var pass = $("#pass").val();
			var rpass = $("#rpass").val();
			
			if( (usr==null) || (usr=="" ) || (usr=="undefined")){
				alert("inserisci username");
				return false;
			}
			
			if((pass==null)||(pass=="")||(pass=="undefined")){
				alert("inserisci una password");
				return false;
			}
			
			if(pass != rpass){
				alert("conferma password non corretta");
				return false;
			}
		});
	
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
			<div class="signIn">
				<h3>Sign In</h3>
				<hr />
				<form:form id="mod" method="post" action="signIn" modelAttribute="newUser" commandName="newUser">
					<h4>Anagrafica</h4>
				
					<table>
					<tr>
					<th>Name:</th>
					<td><input type="text"  name="name" value="${user.name}" ></td>
					<td><form:errors path='name'/></td>
					</tr>
					<tr>
					<th>surname:</th>
					<td><input type="text" name="surname" value="${user.surname}"></td>
					<td><form:errors path='surname'/></td>
					</tr>
					
					<tr>
					<th>cf_pIva:</th>
					<td><input type="text" name="pIva_cf" value="${user.pIva_cf}"></td>
					<td><form:errors path='pIva_cf'/></td>
					</tr>
					
					<tr>
					<th>BirthDate:</th>
					<c:if test="${!empty user.birth}">
					<td><input id="dateBirth" type="date"  name="birth" value="${user.birth}"></td>
					</c:if>
					<c:if test="${empty user.birth}">
					<td><input id="dateBirth" type="date"  name="birth" ></td>
					</c:if>
					<td><form:errors path='birth'/></td>
					</tr>

					<tr>
					<th>City:</th>
					<td><select name="city" id="city" value="${user.city}"></select></td>
					<td>
					<script language="javascript"> populateCountries("city");</script>
 					</td>
 					<td><form:errors path='city'/></td>
 					</tr>
					
					<tr>	
					<th>Street Address:</th>
					<td><input type="text" name="streetAddress" value="${user.streetAddress }"></td>
					<td><form:errors path='streetAddress'/></td>
					</tr>
					
					<tr>
					<th>Telephone Number:</th>
					<td><input type="text" name="telephoneNumber" value="${user.telephoneNumber}"></td>
					<td><form:errors path='telephoneNumber'/></td>
					</tr>
					<tr>
					<tr>
					<th>Email:</th>
					<td><input type="email" name="email"  value="${user.email}"></td>
					<td><form:errors path='email'/></td>
					</tr>
					<tr>
					</table>
							<hr />
					<h4>Account info</h4>
					<table>
					<tr>
					<th>Username:</th>
					<td><input id = "usr" type="text"  name="username"></td>
					<td></td>
					</tr>
					<tr>
					<th>Password:</th>
					<td><input id="pass" type="password"  name="password" ></td>
					<td></td>
					</tr>
					<tr>
					<th>Confirm Password:</th>
					<td><input id="rpass" type="password"  name="reinsPassword"></td>
					<td></td>
					</tr>
					</table>
			
					<p>
					<input type="submit" value="Sign In">
					<c:if test="${!empty after}">
						<input type="hidden" name="after" value="${after}">
						<c:if test="${!empty service}">
							<input type="hidden" name="service" value="${service}">
						</c:if>
					</c:if>
					</p>
					
				</form:form>
			</div>

		</div>
		<div id="footer"></div>
	</div>
</body>
</html>
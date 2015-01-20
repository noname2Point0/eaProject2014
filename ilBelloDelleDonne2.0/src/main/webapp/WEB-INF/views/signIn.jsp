<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="resources/scripts/comuni.js"></script>
<script> populateCountries("city");</script>

<script type="text/javascript">
	$(function() {

		$("#footer").load("resources/template/footer.html");
		$("#header").load("resources/template/header.html");
		
		var date = new Date();
		var dateFormatted = date.getFullYear()+"-"+("0"+(date.getMonth()+1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2);

		$("#dateBirth").attr("value",dateFormatted);
		
		$("#signInForm").submit(function(){
			var pass = $("#pass").val();
			var rpass = $("#rpass").val();
				
			if((pass==null)||(pass=="")||(pass=="undefined")){
				alert("inserisci una password");
				return false;
			}
			
			if(pass != rpass){
				alert("conferma password non corretta");
				return false;
			}
			
			$.ajax({
			      type: 'POST',
			      url: 'signIn',
			      data: $("#signInForm").serialize(),
			      success: function(response){
			      	$("#content").empty();
			      	$("#content").html(response);
			      }
				});
			
			return false;
		});
});
</script>
<c:if test="${!empty message}">
	<p>
		<c:out value="${message}" />
	</p>
	<br>
	<br>
</c:if>
	
	<form:form id="signInForm" method="post" action="signIn" modelAttribute="SignInUser" commandName="SignInUser">
	<br>
		<table class="loginTable">
		<thead>
		<tr><td></td><td>SignIn</td><td></td><td></td><td></td><td></td></tr>
		</thead>
		<tbody>
		<tr><td></td><td>Anagrafica:</td><td></td><td></td><td></td><td></td></tr>
		<tr>
				<td>Name:</td>
				<td><input type="text" name="name" value="${user.name}"></td>
				<td><form:errors path='name' /></td>
		
				<td>Surname:</td>
				<td><input type="text" name="surname" value="${user.surname}"></td>
				<td><form:errors path='surname' /></td>
		</tr>
		<tr>
				<td>cf_pIva:</td>
				<td><input type="text" name="pIva_cf" value="${user.pIva_cf}"></td>
				<td><form:errors path='pIva_cf' /></td>
		
				<td>BirthDate:</td>
				<td><input id="dateBirth" type="date" name="birth" value="${user.birth}"></td>
				<td><form:errors path='birth' /></td>
			</tr>

			<tr>
				<td>City:</td>
				<td><select id="city" name="city"></select></td>
				<td><form:errors path='city' /></td>
				<td>Street Address:</td>
				<td><input type="text" name="streetAddress" value="${user.streetAddress }"></td>
				<td><form:errors path='streetAddress' /></td>
			</tr>

			<tr>
				<td>Telephone Number:</td>
				<td><input type="text" name="telephoneNumber" value="${user.telephoneNumber}"></td>
				<td><form:errors path='telephoneNumber' /></td>
				<td>Email:</td>
				<td><input type="email" name="email" value="${user.email}"></td>
				<td><form:errors path='email' /></td>
			</tr>
			
		<tr><td></td><td>Account:</td><td></td><td></td><td></td><td></td></tr>
			<tr>
				<td>Username:</td>
				<td><input id="usr" type="text" name="account.username"></td>
				<td></td>
				<td>Password:</td>
				<td><input id="pass" type="password" name="account.password"></td>
				<td></td>
			</tr>
			<tr>
				<td></td><td></td><td></td>
				<td>Confirm Password:</td>
				<td><input id="rpass" type="password" name="reinsPassword"></td>
				<td></td>
			</tr>
		<tr><td></td><td><p><input type="submit" value="Sign In"></p></td><td></td><td></td><td></td><td></td></tr>
		
		</tbody>
		</table>
	</form:form>
<br>
<br>

	<!--  
	<h3>Sign In</h3>
	<hr />
	<form:form id="mod" method="post" action="signIn"
		modelAttribute="newUser" commandName="newUser">
		<h4>Anagrafica</h4>

		<table align="center">
			<tr>
				<th>Name:</th>
				<td><input type="text" name="name" value="${user.name}"></td>
				<td><form:errors path='name' /></td>
			</tr>
			<tr>
				<th>surname:</th>
				<td><input type="text" name="surname" value="${user.surname}"></td>
				<td><form:errors path='surname' /></td>
			</tr>

			<tr>
				<th>cf_pIva:</th>
				<td><input type="text" name="pIva_cf" value="${user.pIva_cf}"></td>
				<td><form:errors path='pIva_cf' /></td>
			</tr>

			<tr>
				<th>BirthDate:</th>

				<td><input id="dateBirth" type="date" name="birth"
					value="${user.birth}"></td>
				<td><form:errors path='birth' /></td>
			</tr>

			<tr>
				<th>City:</th>
				<td><select name="city" id="city" value="${user.city}"></select></td>
				<td><script language="javascript"> populateCountries("city");</script>
				</td>
				<td><form:errors path='city' /></td>
			</tr>

			<tr>
				<th>Street Address:</th>
				<td><input type="text" name="streetAddress"
					value="${user.streetAddress }"></td>
				<td><form:errors path='streetAddress' /></td>
			</tr>

			<tr>
				<th>Telephone Number:</th>
				<td><input type="text" name="telephoneNumber"
					value="${user.telephoneNumber}"></td>
				<td><form:errors path='telephoneNumber' /></td>
			</tr>
			
			<tr>
				<th>Email:</th>
				<td><input type="email" name="email" value="${user.email}"></td>
				<td><form:errors path='email' /></td>
			</tr>
			<tr>
		</table>
		<br>
		<hr />
		<h4>Account info</h4>
		<table align="center">
			<tr>
				<th>Username:</th>
				<td><input id="usr" type="text" name="username"></td>
				<td></td>
			</tr>
			<tr>
				<th>Password:</th>
				<td><input id="pass" type="password" name="password"></td>
				<td></td>
			</tr>
			<tr>
				<th>Confirm Password:</th>
				<td><input id="rpass" type="password" name="reinsPassword"></td>
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
	-->
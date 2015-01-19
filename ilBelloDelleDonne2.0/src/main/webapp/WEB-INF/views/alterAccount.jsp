<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="resources/scripts/comuni.js"></script>

<p>il tuo account:</p>
<script type="text/javascript">
$(function(){
	
$("#anForm").submit(function(){

	var name = $("#name").val();
	if(name == "" || name == "undefined" || name == null){
		alert("inserisci il nome");
		return false;
	}

	if((name.length < 2) || (name.length >10) ){
		alert("name size between 2 and 10");
		return false;
	}

	var surname = $("#surname").val();
	if(surname == "" || surname == "undefined" || surname == null){
		alert("inserisci il cognome");
		return false;
	}
	
	if((surname.length < 2) || (surname.length >10) ){
		alert("surname size between 2 and 10");
		return false;
	}
	
	var email = $("#email").val();
	if(email == "" || email == "undefined" || email == null){
		alert("inserisci l' email");
		return false;
	}
	
	var city = $("#city").val();
	if(city=="" || city == "undefined" || city == null){
		alert("inserisci città");
		return false;
	}
	
	var address=$("#address").val();
	if(address == "" || address == "undefined" || address == null){
		alert("inserisci indirizzo");
		return false;
	}
});

$("#mod").submit(function(){
	
	var cpass=$("#cpass").val();
	var pass = $("#inpass").val();
	var rpass = $("#rinpass").val();
	
	if((cpass==null)||(cpass=="")||(cpass=="undefined")){
		alert("inserisci la password corrente");
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
<p>Anagrafica</p>
<form:form id="anForm" action="updateAlterUser" method="post" modelAttribute="updUser">
<table class="mytable">
<thead>
	<tr><th></th><th>current value</th><th>new value</th></tr>
</thead>
	<tbody>
		<tr>
			<th>Name:</th>
			<td>${user.name}</td>
			<td><input id="name" type="text" value="${user.name}" name="name"></input></td>
		</tr>
		<tr>
			<th>Surname:</th>
			<td>${user.surname}</td>
			<td><input id="surname" type="text" value="${user.surname }" name="surname"></input></td>
		</tr>
		<tr>
			<th>Birth date:</th>
			<td>${user.birth}</td>
			<td><input id="birth" type="date" value="${userBirth}" name="birth"></input></td>
		</tr>
		<tr>
			<th>City:</th>
			<td>${user.city}</td>
				<td><select name="city" id="city" value="${user.city}"></select></td>
				<script language="javascript">populateCountries("city");</script>
				
		</tr>
		<tr>
			<th>Address:</th>
			<td>${user.streetAddress}</td>
			<td><input id="address" type="text" value="${user.streetAddress}" name="streetAddress"></input></td>
		</tr>
		<tr>
			<th>TelephoneNumber:</th>
			<td>${user.telephoneNumber}</td>
			<td><input id="telephoneNumber" type="text" value="${user.telephoneNumber}" name="telephoneNumber"></input></td>
		</tr>
		<tr>
			<th>email:</th>
			<td>${user.email}</td>
			<td><input id="email" type="email" value="${user.email }" name="email"></input></td>
		</tr>
		
	</tbody>
</table>
<br>
<input type="hidden" name="account.username" value="${account.username}">
<input type="hidden" value="${user.id}" name="id">
<input type="submit" value="update">
</form:form>

<br>
<p>cambia la tua password</p>
<form:form id="mod" action="updateAlterAccount" method="post" modelAttribute="udpAccount">
<table class="mytable">
	<thead>
		<tr>
			<th></th><th>current password</th>	<th>new password</th><th>confirmpassword</th>
		</tr>
	</thead>	
		<tr>
			<th>password</th>		
			<td><input id="cpass" type="password" name="currentPassword"></td>
			<td><input id="inpass" type="password" name="password"></td>
			<td><input id="rinpass" type="password"></td>
		</tr>
	</tbody>
</table>
<br>
<input type="submit" value="update">
</form:form>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script type="text/javascript" src="resources/scripts/comuni.js"></script>

<p>il tuo account:</p>
<script type="text/javascript">
$(function(){
$("#anForm").submit(function(event){
	
		if($("#city").val()=="-1" || $("#city").val() == "" || $("#city").val() == "undefined" || $("#city").val() == null){
			alert("select city");
			return false;
		}
		
	 	$.ajax({
	      type: 'POST',
	      url: 'updateAlterUser',
	      data: $("#anForm").serialize(),
	      success: function(response) {
	      	$("#divview").empty();
	      	$("#divview").html(response);
	      }
		});
	 
	 return false;
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
			<td><form:errors path='name'/></td>
		</tr>
		<tr>
			<th>Surname:</th>
			<td>${user.surname}</td>
			<td><input id="surname" type="text" value="${user.surname }" name="surname"></input></td>
			<td><form:errors path='surname'/></td>
		</tr>
		<tr>
			<th>Birth date:</th>
			<td>${user.birth}</td>
			<td><input id="birth" type="date" value="${userBirth}" name="birth"></input></td>
			<td><form:errors path='birth'/></td>
			</tr>
		<tr>
			<th>City:</th>
			<td>${user.city}</td>
				<td><select name="city" id="city"></select></td>
				<script language="javascript">populateCountries("city");</script>
				<td><form:errors path='city' /></td>
				
		</tr>
		<tr>
			<th>Address:</th>
			<td>${user.streetAddress}</td>
			<td><input id="address" type="text" value="${user.streetAddress}" name="streetAddress"></input></td>
			<td><form:errors path='streetAddress' /></td>
			</tr>
		<tr>
			<th>TelephoneNumber:</th>
			<td>${user.telephoneNumber}</td>
			<td><input id="telephoneNumber" type="text" value="${user.telephoneNumber}" name="telephoneNumber"></input></td>
			<td><form:errors path='telephoneNumber' /></td>
		</tr>
		<tr>
			<th>email:</th>
			<td>${user.email}</td>
			<td><input id="email" type="email" value="${user.email }" name="email"></input></td>
			<td><form:errors path='email'/></td>
			
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
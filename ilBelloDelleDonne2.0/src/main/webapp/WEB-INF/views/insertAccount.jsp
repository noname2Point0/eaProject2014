<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="resources/scripts/comuni.js"></script>
<script>populateCountries("city");</script>

 <script type="text/javascript">
	$(function() {

		$("#footer").load("resources/template/footer.html");
		$("#header").load("resources/template/header.html");
		
		var date = new Date();
		var dateFormatted = date.getFullYear()+"-"+("0"+(date.getMonth()+1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2);

		$("#dateBirth").attr("value",dateFormatted);
		
		$("#insForm").submit(function(){
			$.ajax({
			      type: 'POST',
			      url: 'insertNewAccount',
			      data: $("#insForm").serialize(),
			      success: function(response) {
			      	$("#divview").empty();
			      	$("#divview").html(response);
			      }
				});
			return false;
		});
});
</script>
<p>inserisci il nuovo account:</p>
<p>username e password saranno automaticamente creati dal sistema</p>
<p>Anagrafica</p>
<form:form id="insForm" action="insertNewAccount" method="post" modelAttribute="insUser" commandName="insUser">
	<table class="mytable">
		<thead>
			<tr>
				<th></th>
				<th>value</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<th>Name:</th>
			<td><input type="text" name="name" value="${user.name}"></input></td>
			<td><form:errors path='name'/></td>
		</tr>
		<tr>
			<th>Surname:</th>
			<td><input type="text" name="surname" value="${user.surname}"></input></td>
			<td><form:errors path='surname'/></td>
		</tr>
		<tr>
			<th>Birth date:</th>
			<td><input id="dateBirth" type="date" name="birth" ></input></td>
			<td><form:errors path='birth'/></td>
			</tr>
		<tr>
			<th>City:</th>
				<td><select name="city" id="city"></select></td>
				<td><form:errors path='city' /></td>
				
		</tr>
		<tr>
			<th>Address:</th>
			<td><input type="text" name="streetAddress" value="${user.streetAddress}"></input></td>
			<td><form:errors path='streetAddress' /></td>
			</tr>
		<tr>
			<th>TelephoneNumber:</th>
			<td><input type="text" name="telephoneNumber"  value="${user.telephoneNumber}"></input></td>
			<td><form:errors path='telephoneNumber' /></td>
		</tr>
		<tr>
			<th>email:</th>
			<td><input type="email"  name="email" value="${user.email}"></input></td>
			<td><form:errors path='email'/></td>
			
		</tr>
		<tr>
				<th>type:</th>
				<td><select name="typeUs">
						<option value="admin">admin</option>
						<option value="employeeWarehouse">warehouser</option>
						<option value="employeeSaloon">coiffeur</option>
				</select></td>
				<td></td>
			</tr>

	</tbody>
</table>
<br>
<input type="submit" value="insert">
</form:form>

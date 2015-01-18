<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="resources/scripts/comuni.js"></script>
 <script type="text/javascript">
	$(function() {

		$("#footer").load("resources/template/footer.html");
		$("#header").load("resources/template/header.html");

		var date = new Date();
		var dateFormatted = date.getFullYear() + "-"
				+ ("0" + (date.getMonth() + 1)).slice(-2) + "-"
				+ ("0" + date.getDate()).slice(-2);

		$("#dateBirth").attr("value", dateFormatted);
		$("#mod2").submit(function() {
			

			var name = $("#name").val();
			if (name == "" || name == "undefined" || name == null) {
				alert("inserisci il nome");
				return false;
			}

			if ((name.length < 2) || (name.length > 10)) {
				alert("name size between 2 and 10");
				return false;
			}

			var surname = $("#surname").val();
			if (surname == "" || surname == "undefined" || surname == null) {
				alert("inserisci il cognome");
				return false;
			}

			if ((surname.length < 2) || (surname.length > 10)) {
				alert("surname size between 2 and 10");
				return false;
			}

			var email = $("#email").val();
			if (email == "" || email == "undefined" || email == null) {
				alert("inserisci l' email");
				return false;
			}

			var city = $("#city").val();
			if (city == "" || city == "undefined" || city=="-1" || city == null) {
				alert("inserisci città");
				return false;
			}

			var address = $("#address").val();
			if (address == "" || address == "undefined" || address == null) {
				alert("inserisci indirizzo");
				return false;
			}

		});
	});
</script>


<p>inserisci il nuovo account:</p>
<p>username e password saranno automaticamente creati dal sistema</p>
<p>Anagrafica</p>
<form:form id="mod2" action="insertNewAccount" method="post"
	modelAttribute="insUser" commandName="insUser">
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
				<td><input id="name" type="text" name="name"></input></td>
			</tr>
			<tr>
				<th>Surname:</th>
				<td><input id="surname" type="text" name="surname"></input></td>
			</tr>
			<tr>
				<th>Birth date:</th>
				<td><input id="dateBirth" type="date" name="birth"></input></td>
			</tr>
			<tr>
				<th>City:</th>
				<td><select name="city" id="city" value="${user.city}"></select></td>
				<td><script language="javascript">populateCountries("city");
				</script>
				</td>
				<td><form:errors path='city' /></td>
			</tr>
			<tr>
				<th>Address:</th>
				<td><input id="address" type="text" name="streetAddress"></input></td>
			</tr>
			<tr>
				<th>TelephoneNumber:</th>
				<td><input id="telephon" type="text" name="telephoneNumber"></input></td>
			</tr>
			<tr>
				<th>email:</th>
				<td><input id="email" type="email" name="email"></input></td>
			</tr>
			<tr>
				<th>type:</th>
				<td><select name="typeUs">
						<option value="admin">admin</option>
						<option value="employeeWarehouse">warehouser</option>
						<option value="employeeSaloon">coiffeur</option>
				</select></td>
			</tr>

		</tbody>
	</table>
	<br>
	<input type="submit" value="insert">
</form:form>
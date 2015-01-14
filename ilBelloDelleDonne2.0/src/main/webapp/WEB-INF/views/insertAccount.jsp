<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<p>inserisci il nuovo account:</p>

<p>Anagrafica</p>
<form:form action="insertNewAccount" method="post" modelAttribute="insUser">
<table class="mytable">

<thead>
	<tr><th></th><th>value</th></tr>
</thead>
	<tbody>
		<tr>
			<th>Name:</th>
			<td><input type="text"  name="name"></input></td>
		</tr>
		<tr>
			<th>Surname:</th>
			<td><input type="text" name="surname"></input></td>
		</tr>
		<tr>
			<th>Birth date:</th>
			<td><input type="date" name="birthString"></input></td>
		</tr>
		<tr>
			<th>City:</th>
			<td><input type="text" name="city"></input></td>
		</tr>
		<tr>
			<th>Address:</th>
			<td><input type="text"  name="streetAddress"></input></td>
		</tr>
		<tr>
			<th>TelephoneNumber:</th>
			<td><input type="text" name="telephoneNumber"></input></td>
		</tr>
		<tr>
			<th>email:</th>
			<td><input type="email"  name="email"></input></td>
		</tr>
		<tr>
			<th>type:</th>
			<td>
		<select name="typeUs" >
  			<option value="admin">admin</option>
  			<option value="employeeWarehouse">warehouser</option>
  			<option value="employeeSaloon">coiffeur</option>
		</select>
		</td>
		</tr>

	</tbody>
</table>
<br>
<input type="submit" value="insert">
</form:form>

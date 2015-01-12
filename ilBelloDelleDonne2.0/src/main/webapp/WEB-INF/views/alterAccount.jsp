<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<p>il tuo account:</p>

<p>Anagrafica</p>
<form>
<table class="mytable">
<thead>
	<tr><th></th><th>current value</th><th>new value</th></tr>
</thead>
	<tbody>
		<tr>
			<th>Name:</th>
			<td>${user.name}</td>
			<td><input type="text" name="name"></input></td>
		</tr>
		<tr>
			<th>Surname:</th>
			<td>${user.surname}</td>
			<td><input type="text" name="surname"></input></td>
		</tr>
		<tr>
			<th>Birth date:</th>
			<td>${user.birth}</td>
			<td><input type="date" name="birth"></input></td>
		</tr>
		<tr>
			<th>City:</th>
			<td>${user.city}</td>
			<td><input type="text"  name="city"></input></td>
		</tr>
		<tr>
			<th>Address:</th>
			<td>${stock.streetAddress}</td>
			<td><input type="text"  name="streetAddress"></input></td>
		</tr>
		<tr>
			<th>TelephoneNumber:</th>
			<td>${user.telephoneNumber}</td>
			<td><input type="text" name="telephoneNumber"></input></td>
		</tr>
		<tr>
			<th>email:</th>
			<td>${user.email}</td>
			<td><input type="email" name="email"></input></td>
		</tr>

	</tbody>
</table>
<br>
<input type="submit" value="update">
</form>

<br>
<p>Account</p>
<form>
<table class="mytable">
	<thead>
		<tr>
			<th></th><th>current value</th>	<th>new value</th><th>confirm password</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>username</th>
			<td>${user.account.username}</td>
			<td><input type="text"  name="username"></td>
			<td></td>
		</tr>
		
		<tr>
			<th>password</th>		
			<td>-</td>
			<td><input type="password" name="password"> </td>
			<td><input type="password" name="reinsPassword"></td>
		</tr>
		
		<tr>
			<th>type</th>
			<td>${user.account.type}</td>
			<td><input type="text" name="type"></td>
			<td></td>
		</tr>
		
	</tbody>
</table>
<br>
<input type="submit" value="update">
</form>
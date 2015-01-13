<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<p>il tuo account:</p>

<p>Anagrafica</p>
<form:form action="updateAlterUser" method="post" modelAttribute="updUser">
<table class="mytable">
<thead>
	<tr><th></th><th>current value</th><th>new value</th></tr>
</thead>
	<tbody>
		<tr>
			<th>Name:</th>
			<td>${user.name}</td>
			<td><input type="text" value="${user.name}" name="name"></input></td>
		</tr>
		<tr>
			<th>Surname:</th>
			<td>${user.surname}</td>
			<td><input type="text" value="${user.surname }" name="surname"></input></td>
		</tr>
		<tr>
			<th>Birth date:</th>
			<td>${user.birth}</td>
			<td><input type="date" value="null" name="birthString"></input></td>
		</tr>
		<tr>
			<th>City:</th>
			<td>${user.city}</td>
			<td><input type="text" value="${user.city }" name="city"></input></td>
		</tr>
		<tr>
			<th>Address:</th>
			<td>${user.streetAddress}</td>
			<td><input type="text" value="${user.streetAddress}" name="streetAddress"></input></td>
		</tr>
		<tr>
			<th>TelephoneNumber:</th>
			<td>${user.telephoneNumber}</td>
			<td><input type="text" value="${user.telephoneNumber}" name="telephoneNumber"></input></td>
		</tr>
		<tr>
			<th>email:</th>
			<td>${user.email}</td>
			<td><input type="email" value="${user.email }" name="email"></input></td>
		</tr>

	</tbody>
</table>
<br>

<input type="hidden" name="account.username" value="${account.username}">
<input type="hidden" value="${user.id}" name="id">
<input type="submit" value="update">
</form:form>

<br>
<p>Account</p>
<form:form action="updateAlterAccount" method="post" modelAttribute="udpAccount">
<table class="mytable">
	<thead>
		<tr>
			<th></th><th>current value</th>	<th>new value</th><th></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>username</th>
			<td>${user.account.username}</td>
			<td><input type="text" value="${user.account.username}" name="username"></td>
			<td></td>
		</tr>
		
		<tr>
			<th>password</th>		
			<td>-</td>
			<td><input type="password" value="${user.account.password }" name="password"></td>
			<td><input type="password" value="" name="reinsPass"></td>
		</tr>
		
		<tr>
			<th>type</th>
			<td>${user.account.type}</td>
			<td><c:choose>
					<c:when test="${user.account.type=='admin'}">
					<input type="text" value="${user.account.type}" name="type">
					</c:when>
					<c:otherwise>
						<input type="hidden" value="${user.account.type}" name="type">
					</c:otherwise>
					</c:choose>
				
			</td>
			<td></td>
		</tr>
		
	</tbody>
</table>
<br>
<input type="submit" value="update">
</form:form>
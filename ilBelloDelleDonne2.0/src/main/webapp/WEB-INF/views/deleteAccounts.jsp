<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<p>click delete button for remove account and relative user</p>
<table class="mytable">
	<thead>
		<tr>
			<th>username</th>
			<th>name</th>
			<th>surname</th>
			<th>role</th>
			<th>remove</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.account.username}</td>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>${user.account.type}</td>
				<td>
					<form action="deleteAccount" method="post">
						<input type="submit" value="remove"> 
						<input type="hidden" name ="identifier" value="${user.account.username}">
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
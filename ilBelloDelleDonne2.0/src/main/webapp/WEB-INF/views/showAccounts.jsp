<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

 <p>all accounts</p>

 <table class="mytable">
	<thead>
		<thead>
		<tr>
			<th>username</th>
			<th>role</th>
			<th>name</th>
			<th>surname</th>
			<th>birth</th>
			<th>city</th>
			<th>street</th>
			<th>telephone Number</th>
			<th>email</th>			
		</tr>
	</thead>
	</thead>
	<tbody>
	
<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.account.username}</td>
				<td>${user.account.type}</td>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>${user.birth }</td>
				<td>${user.city}</td>
				<td>${user.streetAddress}</td>
				<td>${user.telephoneNumber }</td>
				<td>${user.email }</td>
				<td>
			</tr>

 </c:forEach> 
 
	</tbody>
</table>


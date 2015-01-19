<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
 <p>all accounts</p>
 <table id="table" class="mytable">
	<thead>
		<thead>
		<tr>
			<td>id</td>
			<th>username</th>
			<th>name</th>
			<th>surname</th>
			<th>role</th>
		</tr>
	</thead>
	</thead>
	<tbody>

<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.id}</td>
				<td>${user.account.username}</td>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>${user.account.type}</td>
			</tr>
 </c:forEach> 

</tbody>
</table>
</form>
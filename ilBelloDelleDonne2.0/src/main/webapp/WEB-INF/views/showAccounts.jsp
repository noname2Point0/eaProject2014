<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">
$(function(){
	
$("#details").click(function(){
	alert($("#details").val());
});

});
</script>
 <p>all accounts</p>

 <table class="mytable">
	<thead>
		<thead>
		<tr>
			<td>id</td>
			<th>username</th>
			<th>role</th>
			<th>name</th>
			<th>surname</th>
		</tr>
	</thead>
	</thead>
	<tbody>
	
<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.id}</td>
				<td>${user.account.username}</td>
				<td>${user.account.type}</td>
				<td>${user.name}</td>
				<td>${user.surname}</td>
			</tr>
 </c:forEach> 
 
	</tbody>
</table>


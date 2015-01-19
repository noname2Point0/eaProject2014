<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <script type="text/javascript" src="resources/scripts/stupidtable.js"></script> 
 <script type="text/javascript">
 $(document).ready(function(){
 $("#table").stupidtable(); 
	 
 });
 
 </script>
 <p>all accounts</p>
 <table id="table" class="mytable">
	<thead>
		<thead>
		<tr>
			<td  data-sort="int" >id</td>
			<th>username</th>
			<th>name</th>
			<th>surname</th>
			<th>role</th>
		</tr>
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
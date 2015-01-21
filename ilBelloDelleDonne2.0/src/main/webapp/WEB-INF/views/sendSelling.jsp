<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="resources/scripts/jquery.tablesorter.js"></script> 
 <script type="text/javascript" src="resources/scripts/jquery.searcher.js"></script> 
 <script type="text/javascript">
 
 $(function(){
 $("#table").tablesorter(); 
 $("#table").searcher({
	    inputSelector: "#tablesearchinput"
	});
 });
</script>
<div id="tableSearch"><p>Search: <input type="text" id="tablesearchinput" /></div>
<table id="table" class="mySortableTable">
	<thead>
		<tr>
			<th>id</th>
			<th>user</th>
			<th>date order</th>
			<th>date send</th>
			<th>cost</th>
			<th>check send</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="selling" items="${sellings}">
			<tr>
				<td>${selling.id}</td>
				<td>${selling.customer.account.username}</td>
				<td>${selling.dateOrder }</td>
				<td>${selling.dateConsignment }</td>
				<td>${selling.sellingCost }</td>
				<td><form action="sendSelling" method="post">
							<input type="submit" value="send">
						<input type="hidden" name="sellingId" value="${selling.id}">
						</form>
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
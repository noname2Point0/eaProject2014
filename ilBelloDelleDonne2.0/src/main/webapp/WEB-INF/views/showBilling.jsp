<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<div id="tcontent">
			<p>fatture</p>
			<br>
			<div id="tableSearch"><p>Search: <input type="text" id="tablesearchinput" /></div>
 			<table id="table" class="mySortableTable">
				<thead>
					<tr>
						<th>date</th>
						<th>selling</th>
						<th>reserve</th>
						<th>tot</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="billing" items="${billingList}">
						<tr>
							<td>${billing.dateBilling}</td>
							<td>${billing.selling.id}</td>
							<td>${billing.reserve.id}</td>
							<td><c:if test="${!empty billing.selling}">${billing.selling.sellingCost}</c:if><c:if test="${!empty billing.reserve}">${billing.reserve.service.price}</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br> <br>
</div>

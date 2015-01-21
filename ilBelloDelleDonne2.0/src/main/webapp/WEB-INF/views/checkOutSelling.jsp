
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
$(function(){
	
	$("#form").submit(function(){
		
	$.ajax({
	      type: 'POST',
	      url: 'billingSelling',
	      data: $("#form").serialize(),
	      success: function(response) {
	      	$("#divview").empty();
	      	$("#divview").html(response);
	      }
	});
	return false;
	});
});

</script>

<c:if test="${!empty sellings}">
<table class="mytable">
	<thead>
		<tr>
			<th>id</th>
			<th>user</th>
			<th>date order</th>
			<th>date send</th>
			<th>cost</th>
			<th>fattura</th>
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
				<td>
					<form id="form" method="post" action="billingSelling">
					<input type="hidden" name="sellingId" value="${selling.id}">
						<input type="submit" value="check out">
					</form>
				
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>
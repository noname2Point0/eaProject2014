
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<table class="mytable">
	<thead>
		<tr>
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
				<td>${selling.customer.account.username}</td>
				<td>${selling.dateOrder }</td>
				<td>${selling.dateConsignment }</td>
				<td>${selling.sellingCost }</td>
				<td>
					<fom>
					<input type="hidden" name="sellingId" value="${selling.id}">
					<input type="submit" value="check out">
					</fom>
				
				</td>
				</tr>
		</c:forEach>
	</tbody>
</table>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<table class="mytable">
	<thead>
		<tr>
			<th>type</th>
			<th>brand</th>
			<th>description</th>
			<th>price</th>
			<th>quantity</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="stock" items="${stockList}">
			<tr>
				<td>${stock.type}</td>
				<td>${stock.brand}</td>
				<td>${stock.description}</td>
				<td>${stock.price}</td>
				<td>${stock.quantity}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
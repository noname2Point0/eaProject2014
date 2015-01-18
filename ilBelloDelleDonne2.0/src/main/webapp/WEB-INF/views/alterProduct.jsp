	
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
			<th>alter</th>
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
				<td>
				<form:form method="post" action="alterProduct" modelAttribute="altProduct">
					<input type="hidden" name="type" value="${stock.type}">
					<input type="hidden" name="brand" value="${stock.brand}">
					<input type="hidden" name="description" value="${stock.description}">
					<input type="hidden" name="price" value="${stock.price}">
					<input type="hidden" name="quantity" value="${stock.quantity}">
					<input type="hidden" name="id" value="${stock.id}">
					<input type="submit" value="alter">
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
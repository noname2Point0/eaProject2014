<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form:form action="updateAlterUser" method="post" modelAttribute="updUser">
<table class="mytable">
<thead>
	<tr><th></th><th>current value</th><th>new value</th></tr>
</thead>
	<tbody>
		<tr>
			<th>Type:</th>
			<td>${stock.type}</td>
			<td><input type="text" value="${stock.type}" name="type"></input></td>
		</tr>
		<tr>
			<th>Brand:</th>
			<td>${stock.brand}</td>
			<td><input type="text" value="${stock.brand}" name="brand"></input></td>
		</tr>
		<tr>
			<th>Description:</th>
			<td>${stock.description}</td>
			<td><input type="date" value="${stock.description}" name="description"></input></td>
		</tr>
		<tr>
			<th>Price:</th>
			<td>${stock.price}</td>
			<td><input type="text" value="${stock.price}" name="price"></input></td>
		</tr>
		<tr>
			<th>Quantity:</th>
			<td>${stock.quantity}</td>
			<td><input type="text" value="${stock.quantity}" name="quantity"></input></td>
		</tr>
	</tbody>
</table>
<br>
<input type="submit" value="update">
</form:form>
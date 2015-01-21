<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<p>inserisci il prodotto:</p>

<form:form action="insertNewProduct" method="post" modelAttribute="insProduct">
<table class="mytable">
<thead>
	<tr><th></th><th>value</th></tr>
</thead>
	<tbody>
		<tr>
				<th>Product Image:</th>
				<td><input type="file" name="file" id="file"></td>
			</tr>
			<th>Type:</th>
			<td><input type="text"  name="type"></input></td>
		</tr>
		<tr>
			<th>brand:</th>
			<td><input type="text" name="brand"></input></td>
		</tr>
		<tr>
			<th>description:</th>
			<td><input type="text" name="description"></input></td>
		</tr>
		<tr>
			<th>price:</th>
			<td><input type="number" min="1" name="price"></input></td>
		</tr>
		<tr>
			<th>Quantity</th>
			<td><input type="number"  min="1" name="quantity"></input></td>
		</tr>
		<tr>
			<th>Min</th>
			<td><input type="number"  min="1" name="minStock"></input></td>
		</tr>
	</tbody>
</table>
<br>
<input type="submit" value="insert">
</form:form>

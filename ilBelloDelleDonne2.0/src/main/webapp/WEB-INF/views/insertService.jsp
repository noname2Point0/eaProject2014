<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<p>inserisci il prodotto:</p>

<form:form action="insertNewService" method="post" modelAttribute="insService">
<table class="mytable">
<thead>
	<tr><th></th><th>value</th></tr>
</thead>
	<tbody>
		<tr>
		<th>description:</th>
			<td><input type="text" name="description"></input></td>
		</tr>
		<tr>
			<th>price:</th>
			<td><input type="number" min="1" name="price"></input></td>
		</tr>
	</tbody>
</table>
<br>
<input type="submit" value="insert">
</form:form>

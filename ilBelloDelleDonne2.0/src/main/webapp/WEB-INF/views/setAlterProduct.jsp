<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<form:form action="updateAlterUser" method="post" modelAttribute="updUser">
<table class="mytable">
<thead>
	<tr><th></th><th>current value</th><th>new value</th></tr>
</thead>
	<tbody>
		<tr>
			<th>Type:</th>
			<td>${user.name}</td>
			<td><input type="text" value="${user.name}" name="name"></input></td>
		</tr>
		<tr>
			<th>Brand:</th>
			<td>${user.surname}</td>
			<td><input type="text" value="${user.surname }" name="surname"></input></td>
		</tr>
		<tr>
			<th>Description:</th>
			<td>${user.birth}</td>
			<td><input type="date" value="${userBirth}" name="birth"></input></td>
		</tr>
		<tr>
			<th>Price:</th>
			<td>${user.city}</td>
			<td><input type="text" value="${user.city }" name="city"></input></td>
		</tr>
		<tr>
			<th>Quantity:</th>
			<td>${user.streetAddress}</td>
			<td><input type="text" value="${user.streetAddress}" name="streetAddress"></input></td>
		</tr>
	</tbody>
</table>
<br>
<input type="submit" value="update">
</form:form>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
$(function(){
	
	$("#form").submit(function(){
		
		$.ajax({
		      type: 'POST',
		      url: 'setAlterProduct',
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
<form:form id="form" method="post" modelAttribute="altProduct" commandName="altProduct" action="setAlterProduct" >
<table class="mytable">
<thead>
	<tr><th></th><th>current value</th><th>new value</th></tr>
</thead>
	<tbody>
	<tbody>
		<tr>
			<th>Type:</th>
			<td>${stock.type}</td>
			<td><input type="text"  name="type" value="${stock.type}"></input></td>
			
			<td><form:errors path='type'/></td>
		</tr>
		<tr>
			<th>Brand:</th>
			<td>${stock.brand}</td>
			<td><input type="text" name="brand" value="${stock.brand }"></input></td>
			<td><form:errors path='brand'/></td>
		</tr>
		<tr>
			<th>Description:</th>
			<td>${stock.description}</td>
			<td><input type="text" name="description" value="${stock.description}"></input></td>
			<td><form:errors path='description'/></td>
		</tr>
		<tr>
			<th>Price:</th>
			<td>${stock.price}</td>
			<td><input type="number" min="1" name="price" value='<c:if test="${empty stock}">1</c:if><c:if test="${!empty stock}">${stock.price}</c:if>'></input></td>
			<td><form:errors path='price'/></td>
		</tr>
		<tr>
			<th>Quantity</th>
			<td>${stock.quantity}</td>
			<td><input type="number"  min="1" name="quantity" value='<c:if test="${empty stock}">1</c:if><c:if test="${!empty stock}">${stock.quantity}</c:if>'></input></td>
			<td><form:errors path='quantity'/></td>
		</tr>
	</tbody>
</table>
<br>
<input type="hidden" value="${stock.id}" name="id">
<input type="submit" value="update">
</form:form>
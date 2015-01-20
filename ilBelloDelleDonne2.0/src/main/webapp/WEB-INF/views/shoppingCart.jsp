<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
$(function(){
	
	$(".formSubmit").submit(function(){
		var id=$(this).attr("id");
		$.ajax({
		      type: 'GET',
		      url: 'removeToCart',
		      data: $("#"+id).serialize(),
		      success: function(response) {
		      	$("#content").empty();
		      	$("#content").html(response);
		      }
		});	
			return false;
	});
	
	$("#orderForm").submit(function(){
		$.ajax({
		      type: 'GET',
		      url: 'sellingProducts',
		      success: function(response) {
		      	$("#content").empty();
		      	$("#content").html(response);
		      }
		});	
			return false;
	});
		
	
});
</script>

<div id="tcontent">
	<p>il tuo carrello</p>
	<c:choose>
		<c:when test="${!empty message}">
			<p>
				<c:out value="${message}"></c:out>
			</p>
		</c:when>
		<c:otherwise>
			<table class="mytable">
				<thead>
					<tr>
						<th>type</th>
						<th>brand</th>
						<th>description</th>
						<th>price</th>
						<th>remove</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stock" items="${shoppingList}">
						<tr>
							<td>${stock.type}</td>
							<td>${stock.brand}</td>
							<td>${stock.description}</td>
							<td>${stock.price}</td>
							<td>
								<form:form class="formSubmit" id="${stock.id}" action="removeToCart" method="get" modelAttribute="productStock">
							<input id="${stock.id}bt" type="submit" value="remove"> 
							<input type="hidden" name="id" value="${stock.id}"> 
							<input type="hidden" name="type" value="${stock.type}"> 
							<input type="hidden" name="brand" value="${stock.brand}"> 
								<input type="hidden" name="description" value="${stock.description}">
								<input type="hidden" name="price" value="${stock.price}"> 
								<input type="hidden" name="quantity" value="${stock.quantity}">
				</form:form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>
			<br>

			<form id="orderForm" action="sellingProducts" method="get">
				<input type="submit" value="ordina">
			</form>
		</c:otherwise>
	</c:choose>
</div>

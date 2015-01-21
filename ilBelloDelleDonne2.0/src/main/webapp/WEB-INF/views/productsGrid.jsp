<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
$(function(){
	
	$("#showCartForm").submit(function(){
		$.ajax({
    		type: 'GET',
    		url: 'shoppingCart',
    		success: function(response) {
    		$("#content").empty();
    		$("#content").html(response);
    		}
		});
	 	return false;
		});	
	
	$(".box").click(function(){
			var submitId= $(this).attr("id");
			$("#"+submitId+"form").submit(function(){
			 	$.ajax({
				      type: 'GET',
				      url: 'addToCart',
				      data: $("#"+submitId+"form").serialize(),
				      success: function(response) {
				      	$("#content").empty();
				      	$("#content").html(response);
				      }
				});	
			 	return false;
			});
			
		});
});

</script>
<div id="tcontent">

	<c:if test="${!empty stockList}">
		<p>Products</p>
		<p>click add per aggiungere il prodotto al tuo carrello della
			spesa</p>
		<br>
		<c:forEach var="stock" items="${stockList}">
			<div id="${stock.id}" class="box">
				<br>
				<div>
				<img src="product/${stock.id}/image">
				</div>
				<p>
					${stock.type} <br> ${stock.brand} <br>
					${stock.description}<br> ${stock.price} &#8364;<br>
				<form:form action="addToCart" method="get" id="${stock.id}form" modelAttribute="productStock">
					<input id="${stock.id}bt" type="submit" value="add"> <input
						type="hidden" name="id" value="${stock.id}"> <input
						type="hidden" name="type" value="${stock.type}"> <input
						type="hidden" name="brand" value="${stock.brand}"> <input
						type="hidden" name="description" value="${stock.description}">
					<input type="hidden" name="price" value="${stock.price}"> <input
						type="hidden" name="quantity" value="${stock.quantity}">
				</form:form>
				</p>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${empty stockList}">
		<p>Simao spiacenti, ma al momento non sono disponibili prodotti da
			acquistare</p>
	</c:if>
	<br>
	<br>
	<br>
<c:if test="${user.account.type == 'customer'  || empty user}">
<form id="showCartForm" action="shoppingCart" method="get">
	<input type="submit" value="carrello">
</form>
</c:if>
</div>

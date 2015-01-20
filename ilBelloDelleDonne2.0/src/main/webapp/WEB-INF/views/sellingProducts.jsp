<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
$(function(){
	$("#buyForm").submit(function(){
		$.ajax({
		      type: 'GET',
		      url: 'confirmSelling',
		      data: $("#buyForm").serialize(),
		      success: function(response) {
		      	$("#content").empty();
		      	$("#content").html(response);
		      }
		});	
		return false;
	});
});

</script>
<c:if test="${user.account.type =='customer' || empty user.account.type }">
			<p>conferma il tuo ordine d'acquisto</p>
			<p>seleziona la quantita' per ogni prodotto e clicca su acquista</p>
			<br>
			<p>your products:</p>
			
			<form:form id="buyForm" method="get" action="confirmSelling"  modelattribute="productCustomList">
						<c:forEach items="${productCustomList.productsStock}"  var="product"  varStatus="status" >
						
						<table class="mytable">
						<thead>
							<tr>
								<th>type</th>
								<th>brand</th>
								<th>description</th>
								<th>price</th>
								<th>select quantity</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${product.type}</td>
								<td>${product.brand}</td>
								<td>${product.description}</td>
								<td>${product.price}</td>
								<td>
								<input type="number" name="productsStock[${status.index}].quantity" value="1" min="1" max="${product.quantity}"> 
									<input type="hidden" name="productsStock[${status.index}].type" value="${product.type}">
									<input type="hidden" name="productsStock[${status.index}].brand" value="${product.brand}"> 
									<input type="hidden" name="productsStock[${status.index}].description" value="${product.description}">
									<input type="hidden" name="productsStock[${status.index }].price" value="${product.price}">
									<input type="hidden" name="productsStock[${status.index }].id" value="${product.id}">
								</td>
							</tr>
						</tbody>
					</table>
					<br>
					<hr />
					<br>
				</c:forEach>		
				<input type="submit" value="buy" >
			</form:form>
			</c:if>
			<c:if test="${user.account.type !='customer'}">
			<p>il tuo account non ti permette di acquistare nessun prodotto. siamo spiacenti</p>
			</c:if>

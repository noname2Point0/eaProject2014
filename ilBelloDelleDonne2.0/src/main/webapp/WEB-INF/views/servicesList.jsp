<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">

$(function(){
	
	$(".formSubmit").submit(function(){
		var id=$(this).attr("id");
		$.ajax({
		      type: 'POST',
		      url: 'reserveService',
		      data: $("#"+id).serialize(),
		      success: function(response) {
		      	$("#content").empty();
		      	$("#content").html(response);
		      }
		});	
		
			return false;
	});
});
</script>
<c:if test="${!empty serviceList}">
<div id="tcontent">
			<p>Services</p>
			<br> <br>
			<table class="mytable">
				<thead>
					<tr>
						<th>description</th>
						<th>price</th>
						<c:if test="${user.account.type =='customer' || empty user.account.type }">
						<th>click</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="service" items="${serviceList}">
						<tr>
							<td>${service.description}</td>
							<td>${service.price}</td>
							<c:if test="${user.account.type =='customer' || empty user.account.type }">
							<td>
							<form:form class="formSubmit"  id="${service.id}" action="reserveService" method="post" modelattribute="service">
								<input type="submit" value="reserve"> 
								<input type="hidden" name="id" value="${service.id}"> 
								<input type="hidden" name="description" value="${service.description }"> 
								<input type="hidden" name="price" value="${service.price }">
							</form:form>
						</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br> <br>
		<c:if test="${user.account.type =='customer' || empty user.account.type }">
		<p>*lo shampoo viene effettuato per ognuno di questi servizi</p>
		</c:if>
</div>
</c:if>
<c:if test="${empty serviceList}">
<p>Siamo spiacenti, al momento non ci sono servizi da prenotare</p>
</c:if>
	
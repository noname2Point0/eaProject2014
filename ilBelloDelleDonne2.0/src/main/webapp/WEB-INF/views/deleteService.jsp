<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="resources/scripts/jquery.tablesorter.js"></script> 
 <script type="text/javascript" src="resources/scripts/jquery.searcher.js"></script> 
 <script type="text/javascript">
 
 $(function(){
 $("#table").tablesorter(); 
 $("#table").searcher({
	    inputSelector: "#tablesearchinput"
	});
 
	
	$(".formSubmit").submit(function(){
		var id=$(this).attr("id");
		$.ajax({
		      type: 'POST',
		      url: 'deleteService',
		      data: $("#"+id).serialize(),
		      success: function(response) {
		      	$("#divview").empty();
		      	$("#divview").html(response);
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
			<div id="tableSearch"><p>Search: <input type="text" id="tablesearchinput" /></div>
 
 			<table id="table" class="mySortableTable">
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
							<form:form class="formSubmit"  id="${service.id}" action="deleteService" method="post" modelattribute="service">
								<input type="submit" value="delete"> 
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
	
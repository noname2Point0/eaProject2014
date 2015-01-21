<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="resources/scripts/jquery.tablesorter.js"></script> 
 <script type="text/javascript" src="resources/scripts/jquery.searcher.js"></script> 
 <script>
$(function(){
	$("#table").tablesorter(); 
	 $("#table").searcher({
		    inputSelector: "#tablesearchinput"
	});
	 
		$(".formSubmit").submit(function(){
			var id=$(this).attr("id");
			$.ajax({
			      type: 'GET',
			      url: 'setAlterService',
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
<div id="tableSearch"><p>Search: <input type="text" id="tablesearchinput" /></div>
<table id="table" class="mySortableTable">
	<thead>
		<tr>
			<th>description</th>
			<th>price</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="service" items="${serviceList}">
			<tr>
				<td>${service.description}</td>
				<td>${service.price}</td>
				<td>
				<form:form class="formSubmit"  id="${service.id}" modelAttribute="altService" action="setAlterService"> 
					<input type="hidden" name="description" value="${service.description}">
					<input type="hidden" name="price" value="${service.price}">
					<input type="hidden" name="id" value="${service.id}">
					<input id="c" type="submit" value="alter">
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
			      url: 'setAlterProduct',
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
<div id="tableSearch"><p>Search: <input type="text" id="tablesearchinput" /></div>
<table id="table" class="mySortableTable">
<table class="mytable">
	<thead>
		<tr>
			<th>type</th>
			<th>brand</th>
			<th>description</th>
			<th>price</th>
			<th>quantity</th>
			<th>alter</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="stock" items="${stockList}">
			<tr>
				<td>${stock.type}</td>
				<td>${stock.brand}</td>
				<td>${stock.description}</td>
				<td>${stock.price}</td>
				<td>${stock.quantity}</td>
				<td>
				<form:form class="formSubmit"  id="${stock.id}" modelAttribute="altProduct" action="setAlterProduct"> 
					<input type="hidden" name="type" value="${stock.type}">
					<input type="hidden" name="brand" value="${stock.brand}">
					<input type="hidden" name="description" value="${stock.description}">
					<input type="hidden" name="price" value="${stock.price}">
					<input type="hidden" name="quantity" value="${stock.quantity}">
					<input type="hidden" name="id" value="${stock.id}">
					<input id="c" type="submit" value="alter">
					</form:form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

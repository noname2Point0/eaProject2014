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
	
	 
	 $('input[type="submit"]').on('click', function(){
	     $('#addform').data('button', this.name);
	});
	 
	 $("#addform").submit(function(event){
		 var submitButton = $(this).data('button') || $('input[type="submit"]').get(0).name;
		 var qnt = $("#qntAdd").val();
		 $.ajax({
		      type: 'POST',
		      url: 'insertExistProduct',
		      data: "stockId="+submitButton+"&qntS="+qnt,
		      success: function(response) {
		      	$("#divview").empty();
		      	$("#divview").html(response);
		      }
			});
		 alert("ciao");
		 	return false;
	 });	
		 
	$("#form").submit(function(){
		var imgVal = $('#file').val();
        if(imgVal==''){ 
            alert("select an image");
            return false;
        } 
        
		var formData = new FormData($(this)[0]);
			$.ajax({
		      type: 'POST',
		      url: 'insertNewProduct',
			  data: formData,
		        contentType: false,
		    	async: false,
	    	    cache: false,
		        contentType: false,
		        processData: false,
		      success: function(response) {
		      	$("#divview").empty();
		      	$("#divview").html(response);
		      }
			});
		return false;
	});
});
</script>
<p>inserisci un nuovo stock di prodotti:</p>
<form:form id="form" method="post" commandname="insProduct" enctype="multipart/form-data" modelAttribute="insProduct" action="insertNewProduct" >
<table class="mytable">
<thead>
	<tr><th></th><th>value</th></tr>
</thead>
	<tbody>
		<tr>
				<th>Product Image:</th>
				<td><input type="file" name="file" id="file" ></td>
				<td><form:errors path='image'/></td>
		</tr>
		<tr>
			<th>Type:</th>
			<td><input type="text"  name="type" value="${productStock.type}"></input></td>
			<td><form:errors path='type'/></td>
		</tr>
		<tr>
			<th>Brand:</th>
			<td><input type="text" name="brand" value="${productStock.brand }"></input></td>
			<td><form:errors path='brand'/></td>
		</tr>
		<tr>
			<th>Description:</th>
			<td><input type="text" name="description" value="${productStock.description}"></input></td>
			<td><form:errors path='description'/></td>
		</tr>
		<tr>
			<th>Price:</th>
			<td><input type="number" min="1" name="price" value='<c:if test="${empty productStock}">1</c:if><c:if test="${!empty productStock}">${productStock.price}</c:if>'></input></td>
			<td><form:errors path='price'/></td>
		</tr>
		<tr>
			<th>Quantity</th>
			<td><input type="number"  min="1" name="quantity" value='<c:if test="${empty productStock}">1</c:if><c:if test="${!empty productStock}">${productStock.quantity}</c:if>'></input></td>
			<td><form:errors path='quantity'/></td>
		</tr>
		<tr>
			<th>Min Stock</th>
			<td><input type="number"  min="1" name="minStock" value='<c:if test="${empty productStock}">1</c:if><c:if test="${!empty productStock}">${productStock.minStock}</c:if>'></input></td>
			<td><form:errors path='minStock'/></td>
		</tr>
	</tbody>
</table>
<br>
<input type="submit" value="insert">
</form:form>
<br><br>
<p>inserisci il prodotto ad uno stock esistente</p>
<form:form id="addform" action="">
<table>
<tr><td>Quantità di prodotti da aggiungere allo stock: </td><td><input id="qntAdd" type="text"></td></tr>
</table>
<div id="tableSearch"><p>Search: <input type="text" id="tablesearchinput" /></div>
 <table id="table" class="mySortableTable">
	<thead>
		<tr>
			<th>type</th>
			<th>brand</th>
			<th>description</th>
			<th>price</th>
			<th>min stock</th>
			<th>quantity</th>
			<th>add</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="stock" items="${stockList}">
			<tr>
				<td>${stock.type}</td>
				<td>${stock.brand}</td>
				<td>${stock.description}</td>
				<td>${stock.price}</td>
				<td>${stock.minStock}</td>
				<td>${stock.quantity}</td>
				<td><input type="submit" name="${stock.id}" value="add"></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</form:form>
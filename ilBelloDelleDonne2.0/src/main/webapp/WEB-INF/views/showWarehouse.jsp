<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="resources/scripts/jquery.tablesorter.js"></script> 
 <script type="text/javascript" src="resources/scripts/jquery.searcher.js"></script> 
 <script type="text/javascript">
 
 $(function(){
 $("#table").tablesorter(); 
 $("#table").searcher({
	    inputSelector: "#tablesearchinput"
	});
 
 	$('input[type="submit"]').on('click', function(){
    	 $('#sender').data('button', this.name);
	});
 
 
 });
 </script>
 <c:if test="${!empty message }">
 <p>${message}</p>
 </c:if>
<c:if test="${!empty stockList}">
 <p>all products</p>
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
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>
<c:if test="${empty stockList}">
<p>al momento non risultano prodotti da visualizzare</p>
</c:if>
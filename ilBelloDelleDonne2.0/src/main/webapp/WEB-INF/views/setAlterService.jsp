<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
$(function(){
	$("#form").submit(function(){
	var pric = $("#pric").val();
	
	if(pric == "" || pric == null || pric == "undefined"){
		alert("inserisci il prezzo");
		return false;
	}
	
		$.ajax({
	      type: 'POST',
	      url: 'setAlterService',
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
<p>inserisci il prodotto:</p>
<form:form id="form" method="post" modelAttribute="altService" commandName="altService" action="setAlterService" >
<table class="mytable">
<thead>
	<tr><th></th><th>current value</th><th>new value</th></tr>
</thead>
	<tbody>
		<tr>
		<th>description:</th>
			<td>${service.description}</td>
			<td><input type="text" name="description" value="${service.description}"></input></td>
			<td><form:errors path='description'/>
			</td>
		</tr>
		<tr>
			<th>price:</th>
			<td>${service.price}</td>
			<td><input id="pric" type="number" min="1" name="price" value="${service.price}"></input></td>
			<td><form:errors path='price'/>
		</tr>
	</tbody>
</table>
<br>
<input type="hidden" value="${service.id}" name="id">
<input type="submit" value="update">
</form:form>

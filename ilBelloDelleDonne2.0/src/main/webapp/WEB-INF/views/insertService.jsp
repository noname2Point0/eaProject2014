<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
$(function(){
	$("#form").submit(function(){
		var desc = $("#desc").val();
		var pric = $("#pric").val();
		if(desc == "" || desc == null || desc == "undefined"){
			alert("inserisci la descrizione del servizio");
			return false;
		}
		
		if(pric == "" || pric == null || pric == "undefined"){
			alert("inserisci il prezzo");
			return false;
		}
		
	 	$.ajax({
	      type: 'POST',
	      url: 'insertNewService',
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
<form:form id="form" method="post" modelAttribute="insService" action="insertNewService" >
<table class="mytable">
<thead>
	<tr><th></th><th>value</th></tr>
</thead>
	<tbody>
		<tr>
		<th>description:</th>
			<td><input id="desc" type="text" name="description"></input></td>
		</tr>
		<tr>
			<th>price:</th>
			<td><input id="pric" type="number" min="1" name="price"></input></td>
		</tr>
	</tbody>
</table>
<br>
<input type="submit" value="insert">
</form:form>

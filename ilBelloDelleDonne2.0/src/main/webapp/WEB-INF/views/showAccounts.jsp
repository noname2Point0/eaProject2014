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
 
 
 $("#sender").submit(function(event){
	 var submitButton = $(this).data('button') || $('input[type="submit"]').get(0).name;
	    
	 	$.ajax({
	      type: 'POST',
	      url: 'accountDetails',
	      data: "username="+submitButton,
	      success: function(response) {
	      	$("#divview").empty();
	      	$("#divview").html(response);
	      }
		});
	 
	 return false;
 });
 
 });
 
 </script>
 <p>All accounts</p>
 <br>
 <div id="tableSearch"><p>Search: <input type="text" id="tablesearchinput" /></div>
 
<form id="sender">
 <table id="table" class="mySortableTable">
	<thead>
		<tr>
			<th>username</th>
			<th>name</th>
			<th>surname</th>
			<th>role</th>
			<th></th>
		</tr>
	</thead>
	<tbody>

<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.account.username}</td>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>${user.account.type}</td>
				<td>
				<input name="${user.account.username}" id="submit" type="submit" value="details">
				</td>
			</tr>
 </c:forEach> 
</tbody>
</table>
</form>

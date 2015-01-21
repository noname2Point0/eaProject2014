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
 
 $(".formSubmit").submit(function(){
		var id=$(this).attr("id");
		$.ajax({
		      type: 'POST',
		      url: 'deleteAccount',
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
 <p>clicca sul pulsante delete per rimuovere utente e account relativo</p>
 <br>
 <div id="tableSearch"><p>Search: <input type="text" id="tablesearchinput" /></div>
 <table id="table" class="mySortableTable">
	<thead>
		<tr>
			<th data-sort="int">id</th>
			<th>username</th>
			<th>name</th>
			<th>surname</th>
			<th>role</th>
			<th>remove</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.id }</td>
				<td>${user.account.username}</td>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>${user.account.type}</td>
				<td>
					<form  class="formSubmit"  id="${user.account.username}" action="deleteAccount" method="post">
						<input type="submit" value="remove"> 
						<input type="hidden" name ="identifier" value="${user.account.username}">
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
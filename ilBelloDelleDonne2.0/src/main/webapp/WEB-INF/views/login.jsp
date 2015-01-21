<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
$(function(){
	$("#signInLink").click(function(){
	
		$.ajax({
		      type: 'GET',
		      url: 'signIn',
		      success: function(response) {
		      	$("#content").empty();
		      	$("#content").html(response);
		      }
		});
	});
	
	$("#logForm").submit(function(){
		
		$.ajax({
		      type: 'POST',
		      url: 'login',
		      data: $("#logForm").serialize(),
		      success: function(response){
		    	$.ajax({
				      type: 'POST',
				      url: 'navigationBar',
				      success: function(response) {
				      	$("#navigationBar").empty();
				      	$("#navigationBar").html(response);
				      }
				});
		    	
		      	$("#content").empty();
		      	$("#content").html(response);
		      }
			});
		
		return false;
	});
});
</script>
<c:if test="${!empty message}">
	<p>
		<c:out value="${message}" />
	</p>
	<br>
</c:if>
<div>
	
	<form:form id="logForm" method="post" action="login" modelAttribute="logAccount" commandName="logAccount">
	<br>
		<table class="loginTable">
		<thead>
		<tr><td></td><td>login</td><td></td></tr>
		</thead>
		<tbody>
		<tr>
			<td>Username:</td>
			<td><input type="text" name="username"></td>
			<td><form:errors path='username' /></td>
			</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="password"></td>
			<td><form:errors path='password' /></td>
		</tr>
		<tr><td></td><td><p><input type="submit" value="login"></p></td><td></td></tr>
		</tbody>
		</table>
	
	</form:form>
</div>
<br>
<br>
<p>non sei ancora registrato? allora <a id="signInLink" href="#signIn">registrati</a></p>

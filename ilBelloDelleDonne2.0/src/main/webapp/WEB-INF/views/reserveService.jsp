<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript">
	$(function() {

		var date = new Date();
		var tomorrowDate = new Date(date.getTime() + (24 * 60 * 60 * 1000));

		var dateFormatted = tomorrowDate.getFullYear() + "-"
				+ ("0" + (tomorrowDate.getMonth() + 1)).slice(-2) + "-"
				+ ("0" + tomorrowDate.getDate()).slice(-2);

		$("#dateR").attr("value", dateFormatted);
		$("#timeR").attr("value", "08:00:00");

		$("#form").submit(function() {
			$.ajax({
			      type: 'POST',
			      url: 'confirmReserve',
			      data: $("#form").serialize(),
			      success: function(response) {
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
</c:if>
<p>conferma la tua prenotazione</p>
<br>
<p>servizio scelto</p>
<br>

<table class="mytable">
	<thead>
		<tr>
			<th>description</th>
			<th>price</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${service.description}</td>
			<td>${service.price}</td>
		</tr>
	</tbody>
</table>
<br>
	<p>seleziona la data e l'ora per la tua prenotazione</p>
<br>
<form:form id="form" action="confirmReserve" method="post" modelattribute="reserve" commandName="reserve">
<table class="mytable">
		<thead>
			<tr>
				<th>data</th>
				<th>ora</th>
				<th>conferma</th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td><input id="dateR" type="date" name="dateService"><form:errors path='dateService' /></td>
					<td><input id="timeR" type="time" name="time" min="08:00:00" max="19:00:00"></td>
					<td><input type="hidden" name="serviceId" value="${service.id}">
	<input type="submit" value="conferma"></td>
				</tr>
		</tbody>
	</table>
</form:form>

<c:if test="${user.account.type != 'customer'}">
	<p>il tuo account non ti permette di prenotare un servizio, siamo
		spiacenti</p>
</c:if>
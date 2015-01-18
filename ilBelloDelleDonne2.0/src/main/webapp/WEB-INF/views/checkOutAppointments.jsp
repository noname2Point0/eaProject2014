<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:if test="${!empty reserveList}">
<table class="mytable">
	<thead>
		<tr>
			<th>servizio</th>
			<th>data di prenotazione</th>
			<th>data prenotata</th>
			<th>ora prenotata</th>
			<th>nome</th>
			<th>cognome</th>
			<th>prezzo</th>
			<th>fattura</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="reserve" items="${reserveList}">
		<tr>
			<td>${reserve.service.description}</td>
			<td>${reserve.dateOrder}</td>
			<td>${reserve.dateService}</td>
			<td>${reserve.time}</td>
			<td>${reserve.customer.name }</td>
			<td>${reserve.customer.surname }</td>
			<td>${reserve.service.price}</td>
			<td>
				<form action="billingReserve" method="post" >
				<input type="submit" value="check out">
				<input type="hidden" name="reserveId" value="${reserve.id}">
				</form> 
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</c:if>
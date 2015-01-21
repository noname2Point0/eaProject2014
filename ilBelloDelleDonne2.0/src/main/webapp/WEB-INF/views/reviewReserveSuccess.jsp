<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<p>la tua prenotazione è andata a buon fine.<br>Puoi vedere il resto delle tue prenotazioni nella pagina a te riservata</p>
		<p>ricapitolo</p>
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
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${reserve.service.description}</td>
								<td>${reserve.dateOrder}</td>
								<td>${reserve.dateService}</td>
								<td>${reserve.time}</td>
								<td>${reserve.customer.name }</td>
								<td>${reserve.customer.surname }</td>
								<td>${reserve.service.price}</td>
								<td></td>
							</tr>
						</tbody>
					</table>

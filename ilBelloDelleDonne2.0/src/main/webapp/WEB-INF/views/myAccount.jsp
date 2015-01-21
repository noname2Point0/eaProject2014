<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="resources/scripts/menu.js"></script>
<c:if test="${!empty user}">
	<div id="divmenu">
		<div id="divUserInfo">
			<p>informazioni utente:</p>
			<hr />
			nome:
			<c:out value="${user.name}"></c:out>
			<br> cognome:
			<c:out value="${user.surname}"></c:out>
			<br> username:
			<c:out value="${user.account.username}"></c:out>
			<br> email:
			<c:out value="${user.email}"></c:out>
			<br> ruolo:
			<c:out value="${user.account.type}"></c:out>
			<br>
		</div>
		
		<div id="divUserMenu">
			<p>menu:</p>
			<hr />
			account:
			<ul class='userMenu'>
				<li><a id="modificaAccount" href='#'>modifica</a></li>
				<c:if test="${user.account.type == 'admin' }">
					<li><a id="inserisciAccount" href='#'>inserisci</a></li>
					<li><a id="eliminaAccount" href="#">elimina</a>
					<li><a id="visualizzaAccount" href='#'>visualizza accounts</a></li>
				</c:if>
			</ul>
			<c:if test="${user.account.type!='customer' && user.account.type!='employeeWarehouse'}">
					<hr />
						magazzino:
							<ul class="userMenu">
						<li><a id="visualizzaMagazzino" href="#">visualizza</a></li>
						<c:if test="${user.account.type=='admin'}">
							<li><a id="inserisciProdotti" href="#">inserisci prodotto</a></li>
							<li><a id="modificaProdotto" href="#">modifica prodotto</a></li>
						</c:if>
					</ul>
			</c:if>
			
			<c:if test="${user.account.type=='admin'}">
				<hr />
					salone:
					<ul class="userMenu">
					<li><a id="visualizzaServizi" href="#">visualizza</a></li>
					<li><a id="inserisciServizio" href="#">inserisci servizio</a></li>
					<li><a id="modificaServizio" href="#">modifica servizio</a></li>
					<li><a id="eliminaServizio" href="#">elimina servizio</a></li>
				</ul>
			</c:if>
			
			<c:if test="${user.account.type != 'employeeWarehouse' }">
				<hr />
						appuntamenti:						
					<ul class="userMenu">
					<li><a id="visualizzaAppuntamenti" href='#'>visualizza</a></li>
					<c:if test="${user.account.type=='admin'}">
						<li><a id="checkOutAppuntamento" href='#'>check</a></li>
					</c:if>
				</ul>
			</c:if>
			<c:if test="${user.account.type!='employeeSaloon'}">
			<hr />
					ordini:
					<ul class="userMenu">
					<li><a id="visualizzaOrdini" href='#'>visualizza</a></li>
					<c:if test="${user.account.type=='admin'}">
						<li><a id="checkOutOrdine" href='#'>check</a></li>
					</c:if>
					<c:if test="${user.account.type=='employeeWarehouse'}">
						<li><a id="spedisciOrdine" href='#'>spedizione</a></li>
					</c:if>

				</ul>
			</c:if>
			
			
			<c:if test="${user.account.type=='admin'}">
				<hr />
							statistiche:
							<ul class="userMenu">
					<li><a id="visualizzaFatturatoOut" href="#">fatturato out</a></li>
					<li><a id="visualizzaFatturatoIn" href="#">fatturato in</a></li>
					<li><a id="incassoSalone" href="#">incasso salone</a></li>
					<li><a id="incassoECommerce" href="#">incasso e commerce</a></li>
				</ul>
			</c:if>
		</div>
	</div>

	<div id="divview">
	</div>
</c:if>

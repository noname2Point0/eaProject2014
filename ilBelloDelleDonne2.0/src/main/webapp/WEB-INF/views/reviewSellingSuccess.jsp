<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<p>il tuo acquisto è andato a buon fine.<br>
		
		<p>ricapitolo</p>
		
					<table class="mytable">
						<thead>
							<tr>
								<th>id</th>
								<th>date order</th>
								<th>date send</th>
								<th>to</th>
								<th>tot cost</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${selling.id}</td>
								<td>${selling.dateOrder}</td>
								<td>${selling.dateConsignment}</td>
								<td>${selling.customer.account.username}</td>
								<td>${selling.sellingCost}</td>
							</tr>
						</tbody>
					</table>
				
				<br><br>
				<p>products</p>
				<table class="mytable">
				<thead>
					<tr>
						<th>type</th>
						<th>brand</th>
						<th>description</th>
						<th>price</th>
						<th>quantity</th>
						<th>tot</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="stock" items="${stockList}">
						<tr>
							<td>${stock.type}</td>
							<td>${stock.brand}</td>
							<td>${stock.description}</td>
							<td>${stock.price}</td>
							<td>${stock.quantity}</td>
							<td><c:out value="${stock.quantity * stock.price}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

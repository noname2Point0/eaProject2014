$(function(){
	$("#modificaAccount").click(function(){		
			$("#divview").load("alterAccount");
	});
	
	$("#inserisciAccount").click(function(){
		$("#divview").load("insertAccount");
	});
	
	$("#eliminaAccount").click(function(){
		$("#divview").load("deleteAccounts");
	});
	
	$("#visualizzaAccount").click(function(){
		$("#divview").load("showAccounts");
	});
	
	$("#visualizzaAppuntamenti").click(function(){
		
		$("#divview").load("showAppointments");
	});
	
	$("#checkOutAppuntamento").click(function(){
		$("#divview").load("checkOutAppointments");
	});
	
	$("#visualizzaMagazzino").click(function(){
		$("#divview").load("showWarehouse");
	});
	
	$("#modificaProdotto").click(function(){
		$("#divivew").load("alterProduct");
	});
	
	$("#inserisciProdotti").click(function(){
		$("#divview").load("insertProduct");
	});
	
	$("#visualizzaOrdini").click(function(){
		$("#divview").load("showSelling");
	});
	
	$("#spedisciOrdine").click(function(){
		$("#divview").load("sendSelling");
	});
	
	$("#checkOutOrdine").click(function(){
		$("#divview").load("checkOutSelling");
	});
	
	$("#visualizzaServizi").click(function(){
		$("#divview").load("showService");
	});
	
	$("#inserisciServizio").click(function(){
		$("#divview").load("insertService");
	});
	
	$("#modificaServizio").click(function(){
		$("diviview").load("alterService");
	});
	
});
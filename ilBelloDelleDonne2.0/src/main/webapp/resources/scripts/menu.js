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
		alert("ciao");
		$("#divview").load("checkOutSelling");
	});
	
	
	
	$("visualizzaServizi").click(function(){
		alert("ciao");
		$("#divview").load("")
	});
	
	$("inserisciServizio").click(function(){
		alert("ciao");
		$("#divview").load("");
	});
	
});
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
	
});
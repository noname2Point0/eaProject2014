$(function(){
	$("#modificaAccount").click(function(){		
			$("#divview").load("alterAccount");
	});
	
	$("#inserisciAccount").click(function(){
		$("#divview").load("insertAccount");
	});
	
	$("#deleteAccount").click(function(){
		$("#divview").load("deletAccount");
	});
	
	$("#visualizzaAccount").click(function(){
		$("#divview").load("showAccount");
	});

});
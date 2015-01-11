$(function(){
	$("#accLink").click(function(){
		if($("#addedAcc").length){
			$("#addedAcc").remove();
		}else{
			var liel ="<li><a href='#'>modifica</a></li><li><a href='#'>inserisci</a></li><li><a href='#'>elimina</a></li>";
			var ulel = "<ul class='subMenu' id='addedAcc'>"+liel+"</ul>";
			
			$("#account").append(ulel);
		}
	});


	$("#appLink").click(function(){
		if($("#addedApp").length){
			$("#addedApp").remove();		
		}else{
			var liel="<li><a href='#'>modifica</a></li><li><a href='#'>inserisci</a></li><li><a href='#'>elimina</a></li><li><a href='#'>check</a></li>";		
			var ulel ="<ul class='subMenu' id='addedApp'>"+liel+"</ul>";
			$("#appuntamenti").append(ulel);		
		}
	
	});

	$("#ordLink").click(function(){
		if($("#addedOrd").length){
			$("#addedOrd").remove();		
		}else{
			var liel="<li><a href='#'>visualizza</a></li><li><a href='#'>modifica</a></li><li><a href='#'>inserisci</a></li><li><a href='#'>elimina</a></li><li><a href='#'>check</a></li>";	
			var ulel="<ul class='subMenu' id='addedOrd'>"+liel+"</ul>";
			$("#ordini").append(ulel);	
			
		}			
	});

	
	$("#magLink").click(function(){
		
		if($("#addedMag").length){
			$("#addedMag").remove();		
		}else{
			var liel="<li><a href='#'>visualizza</a></li>";
			var ulel="<ul class='subMenu' id='addedMag'>"+liel+"</ul>";		
			$("#magazzino").append(ulel);		
		}

	});
	
	$("#statLink").click(function(){
		
		if($("#addedStat").length){
			$("#addedStat").remove();		
		}else{
			var liel="<li><a href='#'>visualizza</a></li>";
			var ulel="<ul class='subMenu' id='addedStat'>"+liel+"</ul>";	
			$("#statistiche").append(ulel);	
		}
	});

});
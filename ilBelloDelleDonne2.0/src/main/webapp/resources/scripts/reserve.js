function reserveData() {
	// Variabili associate ai campi del modulo
	alert("eccomi sono nello script");
	var date = document.mod.date.value;
	var time = document.mod.time.value;

	
	if(document.mod.time.value.substring(1) < "7" || document.mod.time.value.substring(1) > "19"){
		alert("La prenotazione inserita non rispetta gli orari di apertura/chiusura dell'esercizio.\n+"
				+"Inserire un orario compreso tra 7:00-19:00");

		document.mod.time.focus();
		return false;
	}
	
	/*
    else if(document.modulo.date.value.substring()){

    }
    //Effettua il controllo sul campo DATA DI NASCITA
     if (document.modulo.date.value.substring(2,3) != "/" ||
             document.modulo.nascita.value.substring(5,6) != "/" ||
             isNaN(document.modulo.nascita.value.substring(0,2)) ||
             isNaN(document.modulo.nascita.value.substring(3,5)) ||
             isNaN(document.modulo.nascita.value.substring(6,10))) {

        alert("Inserire nascita in formato gg/mm/aaaa");
        document.modulo.nascita.value = "";
        document.modulo.nascita.focus();
        return false;
    }
    else if (document.modulo.nascita.value.substring(0,2) > 31) {
        alert("Impossibile utilizzare un valore superiore a 31 per i giorni");
        document.modulo.nascita.select();
        return false;
    }
    else if (document.modulo.nascita.value.substring(3,5) > 12) {
        alert("Impossibile utilizzare un valore superiore a 12 per i mesi");
        document.modulo.nascita.value = "";
        document.modulo.nascita.focus();
        return false;
    }
    else if (document.modulo.nascita.value.substring(6,10) < 1900) {
        alert("Impossibile utilizzare un valore inferiore a 1900 per l'anno");
        document.modulo.nascita.value = "";
        document.modulo.nascita.focus();
        return false;
    }
	 */

	return true;

};

$(function(){

	$("#reserve").submit(function(){

		return reserveData();

	});

});
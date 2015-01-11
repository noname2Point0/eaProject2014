function manageData(){
	// Variabili associate ai campi del modulo
	var nome = document.modulo.nome.value;
	var cognome = document.modulo.cognome.value;
	var usernameS = document.modulo.usernameS.value;
	var passwordS = document.modulo.passwordS.value;
	var passwordSR = document.modulo.passwordSR.value;
	var dataNascita = document.modulo.dataNascita.value;
	var viaR = document.modulo.viaR.value;
	var recTelefono = document.modulo.recTelefono.value;
	var email = document.modulo.email.value;
	var pIva_cf = document.modulo.pIva_cf.value;

	// Espressione regolare dell'email
	var email_reg_exp = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-]{2,})+\.)+([a-zA-Z0-9]{2,})+$/;
	var cf_regex = /^([a-z]{6}[0-9]{2}[a-z][0-9]{2}[a-z][0-9]{3}[a-z])$/;

	//Effettua il controllo sul campo NOME
	if ((nome == "") || (nome == "undefined")) {
		alert("Il campo Nome e obbligatorio.");
		document.modulo.nome.focus();
		return false;
	}
	
	//Effettua il controllo sul campo COGNOME
	if ((cognome == "") || (cognome == "undefined")) {
		alert("Il campo Cognome e obbligatorio.");
		document.modulo.cognome.focus();
		return false;
	}
	
	//Effettua il controllo sul campo NICKNAME
	if ((usernameS == "") || (usernameS == "undefined")) {
		alert("Il campo Username è obbligatorio.");
		document.modulo.usernameS.focus();
		return false;
	}
	
	//Effettua il controllo sul campo PASSWORD
	if ((passwordS == "") || (passwordS == "undefined")) {
		alert("Il campo Password e obbligatorio.");
		document.modulo.passwordS.focus();
		return false;
	}
	
	//Effettua il controllo sul campo CONFERMA PASSWORD
	if ((passwordSR == "") || (passwordSR == "undefined")) {
		alert("Il campo Conferma password e obbligatorio.");
		document.modulo.passwordSR.focus();
		return false;
	}
	
	//Verifica l'uguaglianza tra i campi PASSWORD e CONFERMA PASSWORD
	if (passwordS != passwordSR) {
		alert("La password confermata e diversa da quella scelta, controllare.");
		document.modulo.passwordSR.value = "";
		document.modulo.passwordSR.focus();
		return false;
	}
	//Effettua il controllo sul campo DATA DI NASCITA

	 if ((viaR == "") || (viaR == "undefined")) {
		alert("Il campo Indirizzo e obbligatorio.");
		document.modulo.viaR.focus();
		return false;
	}
	 
	 if ((isNaN(recTelefono)) || (recTelefono == "") || (recTelefono == "undefined")) {
		alert("Il campo Telefono è numerico ed obbligatorio.");
		document.modulo.recTelefono.value = "";
		document.modulo.recTelefono.focus();
		return false;
		
	} 

	 if (!email_reg_exp.test(email) || (email == "") || (email == "undefined")) {
		alert("Inserire un indirizzo email corretto.");
		document.modulo.email.select();
		return false;
	}

	 if (((pIva_cf.length != 16) && (pIva_cf.length != 11)) || (pIva_cf == "undefined")) {
		alert("Inserire un P_IVA o un CF corretto. Il CF deve essere composto da 16 cifre. La P_Iva da 11");
		document.modulo.pIva_cf.select();
		return false;
	}
	 if(pIva_cf.length == 16){
		 if(!cf_regex.test(pIva_cf)){
			 alert("codice fiscale non valido");
			 document.modulo.pIva_cf.select();
			 return false;
		 }
	 }
	 
	 return true;

};

$(function(){
	
$("#mod").submit(function(){
	
return manageData();
	
});

});
function reserveData(){
	
	alert("sono nella function reserveData");

	var data = document.mod.data.value;
	var splitData = data.split("-");
	var s1 = document.mod.data.value.substring(1);
	var s2 = document.mod.data.value.substring(2);
	var s3 = document.mod.data.value.substring(3);
	var s4 = document.mod.data.value.substring(4);

	var year = parseInt(splitData[0]);
	var month = parseInt(splitData[1]);
	var day = parseInt(splitData[2]);

	var d = new Date();
	
	var curr_day = d.getDate();
	if (curr_day == 0) {
		curr_day = curr_day + 1;
	}
	var curr_month = d.getMonth();

	if (curr_month == 0) {
		curr_month = curr_month + 1;
	}

	var curr_year = d.getFullYear();


	if (year < curr_year) {
		alert("Inserire una data corretta.\n L'anno selezionato è inferiore a quello corrente");
		document.mod.data.docus();
		return false;
	}
	if (year == curr_year && month < curr_month) {
		alert("Inserire una data corretta.\n Il mese selezionato è inferiore a quello corrente");
		document.mod.data.docus();
		return false;
	}
	if (year == curr_year && month == curr_month && day < curr_day) {
		alert("Inserire una data corretta.\n Il giorno selezionato è inferiore a quello crescente");
		document.mod.data.docus();
		return false;
	} 

	return true;

};

$(function(){
	alert("FUNCTION");
	$("#reserve").submit(function(){

		return reserveData();

	});

});
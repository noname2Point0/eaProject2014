
var regions_arr = new Array("Abruzzo","Basilicata","Calabria","Campania","Emilia-Romagna","Friuli-Venezia Giulia","Lazio","Liguria","Lombardia","Marche","Molise","Piemonte", "Puglia", "Sardegna", "Sicilia", "Toscana", "Trentino-Alto Adige", "Umbria", "Valle d'Aosta", "Veneto");
var provinces = new Array();
provinces[0]="";
provinces[1]="Chieti|L'Aquila|Pescara|Teramo";
provinces[2]="Potenza|Matera";
provinces[3]="Catanzaro|Cosenza|Crotone|Reggio Calabria|Vibo Valentia";
provinces[4]="Avellino|Benevento|Caserta|Napoli|Salerno";
provinces[5]="Bologna|Ferrara|Forlì Cesena|Modena|Parma|Piacenza|Ravenna|Reggio Emilia|Rimini";
provinces[6]="Gorizia|Pordenone|Trieste|Udine";
provinces[7]="Frosinone|Latina|Rieti|Roma|Viterbo";
provinces[8]="Genova|Imperia|Savona|La Spezia";
provinces[9]="Bergamo|Brescia|Como|Cremona|Lecco|Lodi|Mantova|Milano|Pavia|Sondrio|Varese";
provinces[10]="Ancona|Macerata|Pesaro Urbino|Ascoli Piceno"
provinces[11]="Campobasso|Isernia"
provinces[12]="Alessandria|Asti|Biella|Cuneo|Novara|Torino|Verbano-Cusio-Ossola|Vercelli";
provinces[13]="Bari|Barletta-Andria-Trani|Brindisi|Foggia|Lecce|Taranto";
provinces[14]="Cagliari|Carbonia-Iglesias|Medio Campidano|Nuoro|Ogliastra|Olbia-Tempio|Oristano|Sassar";
provinces[15]="Agringento|Caltanissetta|Catania|Enna|Messina|Palermo|Ragusa|Siracusa|Trapani";
provinces[16]="Arezzo|Firenze|Grosseto|Livorno|Lucca|Massa-Carrara|Pisa|Pistoia|Prato|Siena";
provinces[17]="Bolzano|Trento";
provinces[18]="Perugia|Terni";
provinces[19]="Aosta";
provinces[20]="Belluno|Padova|Rovigo|Treviso|Venezia|Verona|Vicenza";

function populateStates(regionElementId, provinceElementId ){
	
	var selectedCountryIndex = document.getElementById( regionElementId ).selectedIndex;

	var stateElement = document.getElementById( provinceElementId );
	
	stateElement.length=0;	
	stateElement.options[0] = new Option('Select Province','');
	stateElement.selectedIndex = 0;
	
	var state_arr = provinces[selectedCountryIndex].split("|");
	
	for (var i=0; i<state_arr.length; i++) {
		stateElement.options[stateElement.length] = new Option(state_arr[i],state_arr[i]);
	}
};

function populateCountries(countryElementId, stateElementId){
	// given the id of the <select> tag as function argument, it inserts <option> tags
	var countryElement = document.getElementById(countryElementId);
	countryElement.length=0;
	countryElement.options[0] = new Option('Select Region','-1');
	countryElement.selectedIndex = 0;
	for (var i=0; i<regions_arr.length; i++) {
		countryElement.options[countryElement.length] = new Option(regions_arr[i],regions_arr[i]);
	}

	// Assigned all countries. Now assign event listener for the states.

	if( stateElementId ){
		countryElement.onchange = function(){
			populateStates( countryElementId, stateElementId );
		};
	}
};

$(function(){
	
	$("#mod").submit(function(){
		
	return populateStates(regionElementId, provinceElementId)();
		
	});
});
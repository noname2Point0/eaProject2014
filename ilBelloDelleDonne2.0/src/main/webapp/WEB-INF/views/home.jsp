<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="utf-8">
<title>il bello delle donne</title>
<link href="resources/styles/bdd.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/scripts/jquery-1.11.1.js"></script>

<script type="text/javascript">
	$(function() {

		$("#footer").load("resources/template/footer.html");
		$("#header").load("resources/template/header.html");
		
		$.ajax({
		      type: 'POST',
		      url: 'navigationBar',
		      success: function(response) {
		      	$("#navigationBar").empty();
		      	$("#navigationBar").html(response);
		      }
			});
		
		$.ajax({
		      type: 'GET',
		      url: 'home',
		      success: function(response) {
		      	$("#content").empty();
		      	$("#content").html(response);
		      }
		});

	});
</script>

</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="navigationBar"></div>

		<div id="content"></div>

	</div>
		<div id="footer"></div>
</body>
</html>

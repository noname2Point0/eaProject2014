<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

$(function(){
	
	$("#homeLink").click(function(){
		$.ajax({
    		type: 'GET',
    		url: 'home',
    		success: function(response) {
    		$("#content").empty();
    		$("#content").html(response);
    		}
		});
	});
	
	$("#chiSiamoLink").click(function(){
		$.ajax({
    		type: 'GET',
    		url: 'chiSiamo',
    		success: function(response) {
    		$("#content").empty();
    		$("#content").html(response);
    		}
		});
	});
	
	$("#productsLink").click(function(){
		$.ajax({
    		type: 'GET',
    		url: 'productsGrid',
    		success: function(response) {
    		$("#content").empty();
    		$("#content").html(response);
    		}
		});
	});
	
	$("#servicesLink").click(function(){
		$.ajax({
    		type: 'GET',
    		url: 'servicesList',
    		success: function(response) {
    		$("#content").empty();
    		$("#content").html(response);
    		}
		});
	});
	
	$("#loginLink").click(function(){
		$.ajax({
    		type: 'GET',
    		url: 'login',
    		success: function(response) {
    		$("#content").empty();
    		$("#content").html(response);
    		}
		});
	});
	
	$("#myAccountLink").click(function(){
		$.ajax({
    		type: 'GET',
    		url: 'myAccount',
    		success: function(response) {
    		$("#content").empty();
    		$("#content").html(response);
    		}
		});
	});
	
	$("#shoppingCartLink").click(function(){
		$.ajax({
    		type: 'GET',
    		url: 'shoppingCart',
    		success: function(response) {
    		$("#content").empty();
    		$("#content").html(response);
    		}
		});
	});
	
});
</script>
<div id="navigationBar">
	<ul>
		<li><a id="homeLink"  href="#home">home</a></li>
		<li><a id="chiSiamoLink" href="#chiSiamo">chi siamo</a></li>
		<li><a id="productsLink" href ="#products">prodotti</a></li>
		<li><a id="servicesLink" href ="#services">servizi</a></li>
		<li></li>
		<li></li>
		<c:if test="${empty user}">
			<li><a id="loginLink" href="#login">login</a></li>
		</c:if>
		<c:if test="${!empty user}">
			<li><a href="logout">logout</a></li>
		</c:if>
		<li><a id="myAccountLink" href="#myAccount">my account</a></li>
		<li><a id="shoppingCartLink" href="#shoppingCart">carrello</a></li>
	</ul>
	<hr />
</div>
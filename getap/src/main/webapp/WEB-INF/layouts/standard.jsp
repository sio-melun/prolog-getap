<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html 
	PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<% String userAgent = request.getHeader("user-agent"); %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
	<head>
	
		<title>
			<tiles:getAsString name="title" />
		</title>
		<style type="text/css" media="screen">
			@import url("<c:url value="/styles/css-framework/typo.css" />");
			@import url("<c:url value="/styles/css-framework/menu.css" />");
			@import url("<c:url value="/styles/css-framework/demos.css" />");
			<% if(userAgent.indexOf("Firefox") != -1) { %>
				@import url("<c:url value="/styles/standard.css" />");
			<% } else if(userAgent.indexOf("Safari") != -1) { %>
				@import url("<c:url value="/styles/standardChrome.css" />");
			<% } %>
		</style>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.20/themes/base/jquery-ui.css" type="text/css" media="all" />
		<link rel="stylesheet" href="http://static.jquery.com/ui/css/demo-docs-theme/ui.theme.css" type="text/css" media="all" />
			
		<script type="text/javascript" src="../../styles/javascript/prefix.js"></script>
		<script type="text/javascript" src="../../styles/javascript/fonctions.js"></script>	
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
		<script type="text/javascript" src="../../styles/javascript/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="../../styles/javascript/jquery-ui.verymin.js"></script>
		<!-- <script src="http://code.jquery.com/ui/1.8.20/jquery-ui.min.js" type="text/javascript"></script>-->		
		<script>
		$(document).ready(function() {
			$('.dataTable').dataTable( {
				"oLanguage": {
					"sEmptyTable": "Aucune donnée correspondante",
					"sLoadingRecords": "Traitement en cour...",
					"sProcessing": "Traitement en cour...",
					"sZeroRecords": "Aucune donnée correspondante",
					"sInfo": "Affiche de _START_ à _END_ sur _TOTAL_ éléments",
					"sInfoEmpty": "Aucun élément",
					"sInfoFiltered": "",
					"sSearch": "<img src='../../images/search.png' width='24' height='24'>",
					"sLengthMenu": 'Affiche <select>'+
					 	'<option value="5">5</option>'+
					 	'<option value="10">10</option>'+
					 	'<option value="15">15</option>'+
					 	'<option value="20">20</option>'+
					 	'<option value="25">25</option>'+
					 	'<option value="-1">Tous</option>'+
					 	'</select> éléments par page',
					"oPaginate": {
						"sFirst": "<<",
						"sLast": ">>",
						"sNext": ">",
						"sPrevious": "<"
					}
				},
				"aaSorting": [  ],
				"sPaginationType": "full_numbers"
			} );
		} );
		</script>
		
     	<script>
  			/* $(document).ready(function() {
   				$("#accordion").accordion({ collapsible: true, activate: false, autoHeight:false});
  			}); */
  			
  			$(function(){
  			// Accordion
  			$("#accordion").accordion({autoHeight: false, header: "h3",  event: "click", collapsible : "true", active:"false"});
  			$("#accordion2").accordion({autoHeight: false, header: "h3",  event: "click", collapsible : "true", active:"false", autoHeight:"false"});		
  			});
  		</script>
  		
        <script>
		  $(document).ready(function() {
		    $("#datepicker" ).datepicker({dateFormat: "yy-mm-dd", 
		    	                          monthNames: ["Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"],
		    	                          dayNames: ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"],
		    	                          dayNamesMin: ["Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa"] 
		    });
		  }); 
		</script>
		
	</head>
	
	<body onload="testRole()">
	
	<div id="banniere">
		<a href="index"><img src="<c:url value="/images/LogoGetap.png"/>" width="155px"
		height="66px"/></a>
	</div>
	
	<tiles:insertAttribute name="navigation" />


	<div id="page">

		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<!-- end header -->
		<div>
			<tiles:insertAttribute name="body" />
		</div>
		<!-- end content -->
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
		<!-- end footer -->
	
	<!-- end page -->
	</body>
</html>
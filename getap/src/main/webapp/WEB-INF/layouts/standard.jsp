<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html 
	PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<title>
			<tiles:getAsString name="title" />
		</title>
		<style type="text/css" media="screen">
			@import url("<c:url value="/styles/css-framework/typo.css" />");
			@import url("<c:url value="/styles/css-framework/layout.css" />");
			@import url("<c:url value="/styles/css-framework/menu.css" />");
			@import url("<c:url value="/styles/standard.css" />");
		</style>
		<script type="text/javascript" src="../../styles/javascript/prefix.js"></script>
		<script type="text/javascript" src="../../styles/javascript/sorttable.js"></script>
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
		<script  src="http://dev.jquery.com/view/trunk/plugins/tablesorter/2.0/jquery.tablesorter.js"></script>
		<script type="text/javascript" src="../../styles/javascript/jquery.tablesorter.min.js"></script>
		<link rel="stylesheet" href="../../styles/themes/blue/style.css" type="text/css" media="print, projection, screen" />
		
     
       <script>
		  $(document).ready(function() {
		    $("#datepicker" ).datepicker({dateFormat: "yy-mm-dd", 
		    	                          monthNames: ["Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"],
		    	                          dayNames: ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"],
		    	                          dayNamesMin: ["Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa"] 
		    });
		  }); 
		</script>
		<script type="text/javascript">
	$(function() {		
		$("#myTable").tablesorter({sortList:[[0,0],[4,1]], widgets: ['zebra']});
		$("#options").tablesorter({sortList: [[0,0]], headers: { 3:{sorter: false}, 4:{sorter: false}}});
	});	
	</script>
		
	</head>
	
	<body>
	
	<div id="banniere">
		<img src="<c:url value="/images/LogoGetap.png"/>" width="155px" height="66px"/>
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
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
		<!-- end footer -->
	</div>
	<!-- end page -->
		<div id="extra1">&nbsp;</div>
		<div id="extra2">&nbsp;</div>
	</body>
</html>
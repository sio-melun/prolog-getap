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
		<script type="text/javascript" src="/styles/javascript/prefix.js"></script>
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
<!DOCTYPE html 
  PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%-- une copie simple du layout --%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
<head>

<title>GETAP : Erreur</title>
<style type="text/css" media="screen">
@import url("/getap/styles/css-framework/typo.css");

@import url("/getap/styles/css-framework/menu.css");

@import url("/getap/styles/css-framework/demos.css");

@import url("/getap/styles/odt.css");

/* TODO : voir le resultat avec Chrome sous ubuntu...  */
@import url("/getap/styles/standard.css");
</style>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.20/themes/base/jquery-ui.css"
	type="text/css" media="all" />
<link rel="stylesheet"
	href="http://static.jquery.com/ui/css/demo-docs-theme/ui.theme.css"
	type="text/css" media="all" />

<script type="text/javascript" src="../../styles/javascript/prefix.js"></script>
<script type="text/javascript"
	src="../../styles/javascript/fonctions.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="../../styles/javascript/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="../../styles/javascript/dataTables.tableTools.min.js"></script>
<script type="text/javascript"
	src="../../styles/javascript/dataTables.tableTools.js"></script>
<script type="text/javascript"
	src="../../styles/javascript/jquery-ui.verymin.js"></script>
<!-- <script src="http://code.jquery.com/ui/1.8.20/jquery-ui.min.js" type="text/javascript"></script>-->
<script>
    $.fn.dataTable.TableTools.defaults.aButtons = [ "copy", "csv", "xls" ];
    $(document).ready(function() {
      $('.dataTable').dataTable( {
        "dom": 'T<"clear">lfrtip',
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
        $("#accordion2").accordion({autoHeight: false, header: "h3",  event: "click", collapsible : "true", active:"false"});
        $("#accordion3").accordion({autoHeight: false, header: "h3",  event: "click", collapsible : "true", active:"false"});
        
        
        });
        
        
      </script>

<script>
      $(document).ready(function() {
        $("#datepicker" ).datepicker({dateFormat: "yy-mm-dd",
                          maxDate: "+0d",
                                    monthNames: ["Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"],
                                    dayNames: ["Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"],
                                    dayNamesMin: ["Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa"] 
        });
      }); 
    </script>

</head>

<body onload="testRole()">

	<div id="banniere">

		<a href="/getap/app/login/index"><img
			src="/getap/images/logos/logoGetapV5.png" width="102px" height="34px" /></a>
	</div>
	<div class="nav">
		<div class="table">
		    	<div class="menuLogin">
					</div>
		</div>
	</div>
	<div id="page">
		<div id="header">
			<div>
				<br />
				<div style="text-align: right;"></div>
			</div>
		</div>
		<!-- end header -->
		<div>
			<h1>Oups !</h1>
			<p>La demande ne peut aboutir.</p>
  </div>
		</div>
</body>
</html>
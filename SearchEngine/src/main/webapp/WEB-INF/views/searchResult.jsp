<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<!-- 
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.dataTables.css">
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
 -->
<link rel="stylesheet" type="text/css"
	href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
	<link rel="stylesheet" href="jquery-ui-1.8.16.custom.css">
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>


<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};

$(document).ready(function() {
//$(document).on('click', '#loadData', function() {
	 var searchWord = $("#keyword").val();
	 if(searchWord == null || searchWord == ""){
		 
	$("#catalog").dataTable( {
		"searching": false,
		"ordering": false,
		"bFilter" : false,               
		"bLengthChange": false,
	    "bProcessing": true,
	    "bServerSide": true,
	    "sort": "position",
	    //bStateSave variable you can use to save state on client cookies: set value "true" 
	    "bStateSave": false,
	    //Default: Page display length
	    "iDisplayLength": 10,
	    //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
	    "iDisplayStart": 0,
	    "fnDrawCallback": function () {
	        //Get page numer on client. Please note: number start from 0 So
	        //for the first page you will see 0 second page 1 third page 2...
	        //Un-comment below alert to see page number
	    	//alert("Current page number: "+this.fnPagingInfo().iPage);    
	    },         
	    "sAjaxSource": "search.action",
	    "aoColumns": [
	        { "mData": "catalogName" },
	        { "mData": "catalogEngineeringDescription" },
	        { "mData": "catalogCommercialDescription" },
	        { "mData": "customerName" },
	        { "mData": "organizationName" },
	        { "mData": "model" },
	    ]
	} );
	
	$("#part").dataTable( {
		"searching": false,
		"ordering": false,
		"bFilter" : false,               
		"bLengthChange": false,
	    "bProcessing": true,
	    "bServerSide": true,
	    "sort": "position",
	    //bStateSave variable you can use to save state on client cookies: set value "true" 
	    "bStateSave": false,
	    //Default: Page display length
	    "iDisplayLength": 10,
	    //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
	    "iDisplayStart": 0,
	    "fnDrawCallback": function () {
	        //Get page numer on client. Please note: number start from 0 So
	        //for the first page you will see 0 second page 1 third page 2...
	        //Un-comment below alert to see page number
	    	//alert("Current page number: "+this.fnPagingInfo().iPage);    
	    },         
	    "sAjaxSource": "searchpart.action",
	    "aoColumns": [
	        { "mData": "partNumber" },
	       // { "mData": "engineeringDescription" },
	       // { "mData": "commercialDescription" },
	       // { "mData": "legacyPartNumber" },
	        //{ "mData": "vendorPartNumber" },
	    ]
	} );
	 }
} );

 $(function() {
    $("#keyword").autocomplete({
        source: function(request, response) {
            $.ajax({
				type: "post",
				url: "autocomplete.action",
				data: request.term.toLowerCase(),
				dataType: "json",
				contentType: 'application/json',
			    mimeType: 'application/json',
                 success: function(data) {
					response($.map(data, function(item) {
					return {
						value: item,
						data : item
					};
					}));
					}, 
            });
        },
        minLength: 2
    });
});
 /* $[ "ui" ][ "autocomplete" ].prototype["_renderItem"] = function( ul, item) {
	 return $( "<li></li>" ) 
	   .data( "item.autocomplete", item )
	   .append( $( "<a></a>" ).html( item ) )
	   .appendTo( ul );
	 }; */
	 $(document).on('click', '#searchSubmit', function() {
		
			 var searchWord = $("#keyword").val();
			 alert("In search"+searchWord);
			 $("#catalog").dataTable( {
			"searching": true,
			"serverSide": true,
			 "ajax": {
		            "url": "specificSearchCatalog.action",
		            "type": "POST",
		            data : searchWord,
		        },
		        "columns": [
		            { "data": "catalogName" },
		            { "data": "catalogEngineeringDescription" },
		            { "data": "catalogCommercialDescription" },
		            { "data": "customerName" },
		            { "data": "organizationName" },
		            { "data": "model" }
		        ]
		    } );

		});
</script>
<style type="text/css">
#tfheader {
	background-color: #c3dfef;
}

#tfnewsearch {
	float: right;
	padding: 20px;
}

.tftextinput {
	margin: 0;
	padding: 5px 15px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	border: 1px solid #0076a3;
	border-right: 0px;
	border-top-left-radius: 5px 5px;
	border-bottom-left-radius: 5px 5px;
}

.tfbutton {
	margin: 0;
	padding: 5px 15px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	color: #ffffff;
	border: solid 1px #0076a3;
	border-right: 0px;
	background: #0095cd;
	background: -webkit-gradient(linear, left top, left bottom, from(#00adee),
		to(#0078a5) );
	background: -moz-linear-gradient(top, #00adee, #0078a5);
	border-top-right-radius: 5px 5px;
	border-bottom-right-radius: 5px 5px;
}

.tfbutton:hover {
	text-decoration: none;
	background: #007ead;
	background: -webkit-gradient(linear, left top, left bottom, from(#0095cc),
		to(#00678e) );
	background: -moz-linear-gradient(top, #0095cc, #00678e);
}
/* Fixes submit button height problem in Firefox */
.tfbutton::-moz-focus-inner {
	border: 0;
}

.tfclear {
	clear: both;
}
</style>
<title>Search Engine</title>
</head>
<body>
	<form:form action="" method="GET">
		<table width="70%"
			style="border: 3px; background: rgb(243, 244, 248);">
			<tr>
				<td>
					<input type="submit" id="loadData" value="LoadData" class="tfbutton">
					<div id="tfheader" align="right">
						<input id="keyword"><input type="submit" id="searchSubmit" value="search" class="tfbutton">
						<div class="tfclear"></div>
					</div>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<table id="catalog" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Catalog Name</th>
								<th>Engineering Description</th>
								<th>Commercial Description</th>
								<th>Customer Name</th>
								<th>Organization</th>
								<th>Model</th>
							</tr>
						</thead>
					</table>
					<table id="part" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Part Name</th>
								<!-- 
								<th>Engineering Description</th>
								<th>Commercial Description</th>
								
								<th>Legacy Part Number</th>
								<th>Vendor Part Number</th>
								 -->
							</tr>
						</thead>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
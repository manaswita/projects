<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- 
<link rel="stylesheet" type="text/css" href="/resources/css/jquery.dataTables.css">
<script src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
 -->

 <script src="<c:url value="resources/static/js/jquery-1.11.2.min.js" />"></script>
 <script src="<c:url value="resources/static/js/jquery-ui.min.js" />"></script>
  <script src="<c:url value="resources/static/js/simplePagination.js" />"></script>
 <link rel="stylesheet" href="<c:url value="resources/static/css/simplePagination.css"/>">

<script type="text/javascript">


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
 $(document).on('click', '#searchSubmit', function() {
	    
	   var search = $("#keyword").val();
	   var type = $("#dropdown").val();
	   
	   $.ajax({
	    type: "POST",
	       url:"specificSearchCatalog.action?search="+search+"&type="+type,
	       dataType:"JSON",
	       contentType: 'application/json; charset=utf-8',
	        data: {
	    	   search:search,
	    	   type: type} , 
	       success: function (response) {
	    	   $('#searchResult').empty();
	    	   $('#searchResult').append('<tbody>');
	           $.each(response, function (i, item) {
	        	   $('#searchResult').append('<tr><td>' + JSON.stringify(item) + '</td></tr>');
	           });
	           $('#searchResult').append('</tbody>');
	            page(); 
	       },
	       error: function () {
	           alert("error"+data+responsedata);
	       }
	   });
	   return false;
	  });
 
  function page(){
	 alert("in jquery");
     var items = $("table.searchResult  tbody tr");
     alert(items.html());
     var numItems = items.length;
     var perPage = 5;
     // only show the first 5 (or "first per_page") items initially
     items.slice(perPage).each(function() {
         // "this" represents each but the first 5 trs
        console.log($(this));
         $(this).hide();
         
        
     });
     // now setup your pagination
     $(".pagination-page").pagination({
         items: numItems,
         itemsOnPage: perPage,
         cssStyle: "light-theme",
         onPageClick: function(pageNumber) { // this is where the magic happens
             // someone changed page, lets hide/show trs appropriately
             var showFrom = perPage * (pageNumber - 1);
             var showTo = showFrom + perPage;
             items.hide() // first hide everything, then show for the new page
                  .slice(showFrom, showTo).each(function() {
                     $(this).show();
                 });
         }
     });
 };
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
	<form:form action="" method="GET" id="from" commandName="ser">
		<table width="70%"
			style="border: 3px; background: rgb(243, 244, 248);">
			<tr>
				<td>
					<div id="tfheader" align="right">
						<input type="text" id="keyword" name="keyword">
						<input type="submit" id="searchSubmit" value="search" class="tfbutton"/> 
						<select name="dropdown" id="dropdown">
							<option value="all" >All</option>
							<option value="part">Part Type</option>
							<option value="catalog">Catalog Type</option>
							<option value="prod">Product Catalog</option>
							<option value="category">Product Category</option>
						</select>
						<div class="tfclear"></div>
					</div>
				</td>
			</tr>
		</table>
		 <div class="pagination-page" style="border:1px solid; height:100px"></div>		
		<table id="searchResult" class="searchResult" border="1">
		</table>	
	</form:form>
</body>
</html>
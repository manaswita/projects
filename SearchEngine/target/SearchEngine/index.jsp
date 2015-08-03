<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<!-- 
	<script type="text/javascript"	src="//code.jquery.com/jquery-1.10.2.min.js"></script> -->
	<script src="<c:url value="/resources/static/js/jquery-1.11.2.min.js" />"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Engine</title>
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
	</script>
</head>
<body>	
	<table>
		<tr><td><input type="text" id="keyword" name="keyword"></td></tr>
	</table>		

</body>
</html>
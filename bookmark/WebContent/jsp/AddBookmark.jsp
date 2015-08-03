<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_includes.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="App.Title"></spring:message></title>
<script src="js/nicEdit-latest.js" type="text/javascript"></script>
<script type="text/javascript">bkLib.onDomLoaded(nicEditors.allTextAreas);</script>
<link href="css/table.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="title">Add Bookmarks</div>
<form:form action="addBookmark.do" method="post" modelAttribute="bookmarkBean">
<fieldset><legend><b>Add Bookmarks</b></legend>
	<table>
			<tr>
				<td style="color:red;font-size: small;" colspan="3">${ERROR_MSG}</td>
			</tr>
			<tr>
				<td style="color:blue;font-size: small;" colspan="3">* Indicates mandatory fields</td>
			</tr>
			<tr>
				<td>URL *</td>
				<td><form:input size="80px" path="url" maxlength="2083"/></td>
				<td align="left"> <form:errors path="url" cssStyle="color:red"></form:errors> </td> 
			</tr>
			<tr>
				<td>Title *</td>
				<td width="70"><form:input size="80px" path="title" maxlength="80"/></td>
				<td align="left"><form:errors path="title" cssStyle="color:red"></form:errors></td>
				<td width="20px"/>
			</tr>
			<tr>
				<td>Notes</td>
				<td width="70"><form:textarea cols="61" rows="5" path="notes"/></td>
				<td align="left"><form:errors path="notes" cssStyle="color:red"></form:errors></td>
			</tr>
			<tr>
				<td>Tags</td>
				<td><form:input size="80px" path="tagString" /></td>
			</tr>
	</table>
	<p>
		<a href="searchBookmarks.do" class="button_example" title="Cancel">CANCEL</a>
		<input type="submit" value="SAVE BOOKMARK" class="button_example" title="Save Bookmark" />
	</p>
</fieldset>
</form:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<table class="center-panel" cellpadding="3" cellspacing="3"
		align="center">
		<tr>
			<td height="100" colspan="2"><tiles:insertAttribute
					name="header" /></td>
		</tr>
		<tr>
			<td height="750" valign="middle"  width="10"><tiles:insertAttribute name="menu" /></td>
			<td width="1050" valign="top"><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td height="30" colspan="2"><tiles:insertAttribute name="footer" />
			</td>
		</tr>
	</table>

</body>
</html>

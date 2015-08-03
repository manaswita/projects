<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<style type="text/css">
h1 {font-size:250%;}
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</style>
</head>
<body>
<br>
<br>
<br>

<p align="center">
<img src="../images/hotdeals1.jpg"/>
<s:form>
<table align="center" border="1" width="30%">
			<s:iterator value="populateList">
			<tr>
			<td>
			<h1>
			<s:property />
			</h1>
						</td>
			</tr>

			</s:iterator>
			<s:submit type="image" align="center" src="../images/home.jpg"  action="home" />
</table> 

			</s:form>
			</p>
</body>
</html>
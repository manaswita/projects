<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
</head>
<body>
	<h1>GO TO MENU</h1>
	<s:form>
	<a href="<s:url action='pizzaList.action'/>"><img src="../images/SelectPizza.jpg"></a><br><br><br><br><br><br>
	<a href="<s:url action='sideList.action'/>"><img src="../images/ContinueToSelectSideDish.jpg"></a><br><br><br><br><br><br>
	<a href="<s:url action='dessertList.action'/>"><img src="../images/ContinueToSelectDesserts.jpg"></a><br><br><br><br><br><br>
	<a href="<s:url action='beverageList.action'/>"><img src="../images/ContinueToBeverages.jpg"></a><br><br><br><br><br><br>
	</s:form>

</body>
</html>
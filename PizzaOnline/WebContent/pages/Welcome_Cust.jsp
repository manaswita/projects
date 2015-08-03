<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.impetus.pizzaonline.model.User"%>
<html>
<head>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>

</head>
<body>
<%
	User user = new User();
	user = (User) session.getAttribute("getUser");
	String role = user.getRole();
	String userName=user.getName();
%>

<center>
			<font color="Red"><s:actionmessage/></font>
		</center>
<h1 align="center">Congratulations <%=userName%>!</h1>
<h3 align="center">please proceed to order selection!Click on any one item below..</h3>
 <s:form action="pizzaList"> 

<center><a href="<s:url action='pizzaList.action'/>"><img src="../images/pizzamenu.gif"></a></center><br>
<a href="<s:url action='sideList.action'/>"><img src="../images/sideDishmenu.jpg" align="right"></a>
<a href="<s:url action='beverageList.action'/>"><img src="../images/beverage.jpg" align="left"></a>

<center><a href="<s:url action='dessertList.action'/>"><img src="../images/desserts.jpg" ></a></center>

 </s:form>
</body>
</html>
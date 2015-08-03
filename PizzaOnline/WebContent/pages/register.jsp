<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<title>user profile</title>
</head>
<body>
<h1 align="center">WELCOME TO PIZZA ITALIANO!!</h1>
<h2 align="center">Register and be a part of PIZZA ITALIANO FAMILY!!</h2>
<center><font color="Red"><s:actionerror /></font></center>
	<s:form action="registerUser" method ="post">
<table align="center" bordercolor="red">
<s:radio name="delivery" label="Select "
					list="{'Pick Up','Home Delivery'}" />

<tr>
	<s:textfield name="name" label="Name:"/>

</tr>
<tr>
<s:textfield name="flatNo" label="Flat/Door No:"/>
</tr>
<tr>
<s:textfield name="bldgNo" label="Building Name/No:"/>
</tr>
<tr>
<s:textfield name="lane" label="Lane/Street/Road"/>
</tr>
<tr>
<s:textfield name="area" label="area*"/>
</tr>
<tr> 
 <s:checkboxlist list="{'Bangalore','Noida','Indore'}" name="city" label="City*" /> 	
</tr>
<tr>
<s:textfield name="pin" label = "Pincode*"/>
</tr>
<tr>
<s:textfield name="mobile" label="Mobile No*"/>
</tr>
<tr>
<s:textfield name="email" label="Email_id"/>
</tr>
<tr>
<s:textfield name="userName" label="username*"/>
</tr>
<tr>
<s:password name="password" label="new password*"/>
</tr>
<tr>
 <s:password name="confirmPassword" label="Confirm Password "/>
 </tr>
</table>
<s:submit type="image" value="Submit" src="../images/images.jpg" align="center" width="50"/><br><br>
<br>
<br>
<center><a href="../pages/Login.jsp"><img src="../images/home.jpg" ></a></center>
</s:form>
</body>
</html>
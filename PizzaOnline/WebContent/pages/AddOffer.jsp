<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<title>Add Offer</title>
</head>
<body>
<h1 align="center">WELCOME TO PIZZA ITALIANO!!</h1>
<h2 align="center">Please enter your new offer</h2>
<center><font color="Red"><s:actionerror/></font></center>
	<s:form action="insertOffer" method ="post">
<table align="center" bordercolor="red" border = "1" width="30%">
<tr>
	<s:textfield name="coupon" label="Coupon Name:"/>

</tr>
<tr>
<s:textfield name="description" label="Description"/>
</tr>
<s:checkboxlist name="checkList" label="Choose your days" list="daysList"/>
<tr>
<s:textfield name="discount" label="Discount(%)"/>
</tr>
<tr>
<s:textfield name="minBill" label="Min Bill Amount"/>
</tr>
<tr>
<s:textfield name="startDate" label="Starting Date(dd-mm-yyyy)"/>
</tr>
<tr>
<s:textfield name="endDate" label="Ending Date(dd-mm-yyyy)"/>
</tr>
</table>
<s:submit type="image" value="Submit"  src="../images/submit_button.gif" align="center" width="50"/>
</s:form>
<h2 align="center">Please enter here to remove an item</h2>
<s:form action="removeOffer">
<table align="center" width="30%">
<tr>
	<s:select name="selectOffer" label="Offer Name:" list="populateList"/>
</tr>
</table>
<s:submit type="image" value="Submit" src="../images/submit_button.gif" align="center" width="50"/>
</s:form>
</body>
</html>
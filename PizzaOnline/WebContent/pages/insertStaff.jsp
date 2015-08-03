<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert Staff</title>
</head>
<body>
<h1 align="center">WELCOME TO STAFF REGISTERATION PAGE</h1>
<center><font color="Red"><s:actionerror/></font></center>
<s:form action="registerStaff">

<table align="center">
<tr>
<s:textfield name="name" label="Name*"/>
</tr>
<tr>
<s:textfield name="designation" label="Designation*"/>
</tr>
<tr>
<s:textfield name="location" label="Store Location*"/>
</tr>
<tr>
<s:textfield name="city" label="City*"/>
</tr>
<tr>
<s:textfield name="mobile" label="Mobile No*"/>
</tr>
<tr>
<s:textfield name="dob" label="Date of Birth"/>
</tr>
<tr>
<s:textfield name="email" label="Email_id*"/>
</tr>
<tr>
<s:textfield name="userName" label="username*"/>
</tr>
<tr>
<s:password name="password" label="new password*"/>
</tr>
<s:submit type="image" value="Register" src="../images/submit_button.gif" align="center"/>
</table>
</s:form>
<h2 align="center">PLEASE REMOVE STAFF USING THE FORM BELOW</h2>
<s:form action="removeStaff">
<table align="center">
<tr>
<s:select name="name" label="Name" list="populateList"/>
</tr>
<s:submit type="image" src="../images/submit_button.gif" value="Remove" align="center"/>
</table>


</s:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<title>Register user</title>
</head>
<body>
<center><font color="Red"><s:actionerror /></font></center>
<s:actionmessage/>
<s:form action="registerCust" method="post">

<table align="center">
<tr>
<td><s:textfield name="mobile" label="Mobile No*"/></td>
<td><s:submit action="validateCust"/></td>
</tr>
<tr>
<s:textfield name="email" label="Email_id"/>
</tr>


<tr>
	<s:textfield name="name" label="Name:"/>

</tr>
<tr>
<s:textfield name="flatNo" label="Flat/Door No:"/>
</tr>
<tr>
<s:textfield name="bldgName" label="Building Name/No:"/>
</tr>
<tr>
<s:textfield name="lane" label="Lane/Street/Road"/>
</tr>
<tr>
<s:textfield name="area" label="area*"/>
</tr>
<tr> 
 	<s:textfield name="city" label="City"/>
</tr>
<tr>
<s:textfield name="pin" label = "Pincode*"/>
</tr>
</table>
<s:submit type="image" value="Submit" src="../images/images.jpg" action="registerCust" align="center" width="50"/>
</s:form>
</body>
</html>
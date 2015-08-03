<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<title>Forgot Password</title>
</head>
<body>
<center><h1>Please enter your username and email address</h1></center>
<s:form name="form" action="forgotPassword" method="post" >
<center><font color="Red"><s:actionerror/></font></center><br><br><br><br><br><br><br>
<table align="center" >
<s:textfield name="userName" label="Enter Username" />
<s:textfield name="email" label="Enter valid Email Address:" />
<s:submit />
</table>
</s:form>
</body>
</html>
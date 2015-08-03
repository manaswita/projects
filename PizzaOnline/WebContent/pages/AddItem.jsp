<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page language="java" import="javazoom.upload.*,java.util.*"%>


<jsp:useBean id="upBean" scope="page" class="javazoom.upload.UploadBean">
	<jsp:setProperty name="upBean" property="folderstore"
		value="c:/uploads" />
</jsp:useBean>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<title>Add Item</title>
</head>
<body>
	<h1 align="center">WELCOME TO PIZZA ITALIANO!!</h1>
	<h2 align="center">Please enter your new Item</h2>

	<s:form method="post" enctype="multipart/form-data">
		<center>
			<font color="Red"><s:actionerror /></font>
		</center>
		<table align="center" border='1' bordercolor="blue" height="100%">

			<s:textfield name="itemName" label="Item Name:" />
			<s:textfield name="itemCategory" label="Item Category:" />
			<%-- <s:textfield name="itemType" label="Type of Item:" /> --%>
			<s:radio name="itemType" label="Choose Type"
				list="{'VEGETARIAN','NONVEGETARIAN'}" />
			<s:select name="itemSize" label="Choose Size" list="chooseSize" />
			<s:textfield name="description" label="Description" />
			<s:textfield name="itemPrice" label="Price" />
			<s:file name="userImage" label="User Image" />
		</table>
		<s:submit type="image" action="insertItem" value="Submit"
			src="../images/submit_button.gif" align="center" width="50" />
	</s:form>
	<h2 align="center">Please enter your new Topping</h2>
	<s:form method="post" action="insertTopping">
		<table align="center" bordercolor="red" height="100%">

			<s:textfield name="tpgName" label="Topping Name:" />
			<s:textfield name="tpgCategory" label="Topping Category:" />
			<s:textfield name="tpgType" label="Type of Topping:" />
			<s:textfield name="tpgPrice" label="Price" />
		</table>
		<s:submit type="image" value="Submit" src="../images/submit_button.gif"
			align="center" width="50" />
	</s:form>
	<h2 align="center">please enter here to remove an item</h2>
	<s:form action="removeItem">
		<table align="center" height="100%" border="1">

			<s:select name="itemName" label="Item Name:" list="populateList" />


			<s:select name="itemSize" label="Choose Size" list="chooseSize" />

		</table>
		<s:submit type="image" value="Submit" src="../images/submit_button.gif" align="center" width="50" />
	</s:form>
	<h2 align="center">please enter here to remove a Topping</h2>
	<s:form action="removeTopping">
		<table align="center" height="100%" border="1">

			<s:select name="tpgName" label="Topping Name:" list="popTpgList" />


			<s:radio name="tpgType" label="Choose Type"
				list="{'VEGETARIAN','NONVEGETARIAN'}" />

		</table>
		<s:submit type="image" value="Submit" src="../images/submit_button.gif" align="center" width="50" />
	</s:form>
</body>
</html>
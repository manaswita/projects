<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
<h1 align=center>PAYMENT SUCCESSFUL</h1>
<h2 align="center">THANK U FOR CHOOSING PIZZA ITALIANO</h2>
	<s:form action="order">
		<table border="1" cellpadding="5" cellspacing="5" width="70%" align="center">
			<tr class="regtxt">

				<th>Item</th>
				<th>NUmber Of Items</th>
				<th align="left">Tax</th>
				<th>Discount</th>
				<th align="left">Total Price</th>



			</tr>

			
				<tr>
					<td ><s:property value="items" /></td>
					<td ><s:property value="numberItems" /></td>
					<td ><s:property value="tax" /></td>
					<td ><s:property value="discount" /></td>
					<td ><s:property value="totalPrice" /></td>
			
		</table>
		<s:submit type="button" label="BACK TO MAIN MENU"
			align="center" action="pizzaList" />
		<br>
		<br>
		<%-- <center><a href="<s:url action='pages/pizzaList.action'/>">BACK TO MAIN MENU</a></center> --%>

</s:form>
</body>
</html>
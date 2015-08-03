<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.impetus.pizzaonline.model.ItemModel"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add To Cart</title>
</head>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<body>
	<h1 align=center>YOUR SELECTED ITEMS</h1>
	<h2 align="center">CHOOSE WISELY,TO HAVE A MEAL NICELY!!!!!</h2>
	<s:form action="cart">
		<table border="1" cellpadding="5" cellspacing="5" width="90%" align="center">
			<tr class="regtxt">
				<th>Select</th>
				<th>Item</th>
				<th>Size</th>
				<th align="left">Quantity</th>
				<th>Toppings</th>
				<th align="left">Total Price</th>



			</tr>
		<s:if test="(cartList!=null||!carList.isEmpty())">
			<s:iterator value="cartList">
				<tr>
					<td><s:checkbox name="removeCartItem" fieldValue="%{itemName}"
							value="defaultCart" theme="simple" /></td>
					<td><s:property value="itemName" /></td>
					<td><s:property value="itemSize" /></td>
					<td><s:property value="quantity" /></td>

					<td><s:property value="toppingName" /></td>
					<td><s:property value="totalPrice" /></td>
			</s:iterator>
		</s:if>
		</table><br>
		<br>
		<table border="1" cellpadding="5" cellspacing="5" width="90%" align="center">
		<tr class="regtxt">
				<th>TAX(12% OF TOTAL)</th>
				<th>HOME DELIVERY CHARGES</th>
				<th>DISCOUNT</th>
				<th>TOTAL PRICE(including tax)</th>
		</tr>
			<tr>
				<td><s:property value="tax" /></td>
				<td><s:property value="deliveryCharges" /></td>
				<td><s:property value="discount" /></td>
				<td><s:property value="totalPrice" /></td>
			</tr>
		</table>
		<%-- <s:submit type="image" src="../images/ContinueToSelectSideDish.jpg"
			align="center" action="remove" /> --%>
		<s:submit type="image" src="../images/remove.jpg" align="center" action="removeCart"
			label="REMOVE" />
		<br>
		
		<br>
		<%
		List<ItemModel> cartList= (List<ItemModel>)session.getAttribute("cartList");
		Integer cartSize=cartList.size();
		System.out.println(cartSize);
	%>
	
<table align="center" width="70%" >
	<tr>
	<%if  (null== cartSize  || cartSize==0){%>
	
	<td align="center">PAYMENT CANNOT BE DONE </td>
	
	<%}else{ %>
	
		<td align="center"><a href="<s:url action='payment.action'/>"><img
				src="../images/ContinueToPayment.jpg"></a></td>
	
			<%} %>
	</tr>
</table>
		


	</s:form>
</body>
</html>
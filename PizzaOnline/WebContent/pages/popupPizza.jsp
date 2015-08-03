<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>pop up Pizza</title>
</head>
<body onload="javascript:closeWindow()">

	<s:form name="toppingForm" action="toppingList" method="post">

		<h2 align="center">Select your topping , from the wide variety
			that we fling!</h2>
		<table align="center">

			<tr class="regtxt">

				<th>Select</th>

				<th align="left">Name</th>

				<th align="left">Type</th>

				<th>Price</th>

			</tr>

			<s:iterator value="toppingList">

				<tr>

					<td><s:checkbox name="selectTopping" fieldValue="%{name}"
							value="defaultTopping" theme="simple" /></td>

					<td class="regtxtLegals a:link"><s:property value="name" /></td>

					<td width="100"><s:if test="%{type=='VEGETARIAN'}">
							<img src="../images/Veg.gif"></td>
					</s:if>
					<s:if test="%{type=='NONVEGETARIAN'}">
						<img src="../images/Nonveg.gif">
						</td>
					</s:if>

					<td class="regtxtLegals a:link"><s:property value="price" /></td>

				</tr>

			</s:iterator>

		</table>
		<br>

		<table>
			<tr height="20"></tr>
			<font color="Red"><s:actionerror/></font>
			<tr>
				<td class="regtxt"><s:radio list="chooseSize"
						name="chooseSize" value="defaultSize" label="Choose your size" />
				</td>
			</tr>
			<tr>
				<td class="regtxt"><s:select name="selectQuantity"
						list="quantity" label="Select the number of Pizzas" headerKey="-1"
						headerValue="Select Number" cssClass="regtxt" /></td>
			</tr>
		</table>
		<%
			String pizzaName = request.getParameter("pizzaName");
		%>
		<s:hidden name="pizzaName" value="%{pizzaName}" />
		<s:hidden id="checkFlag" name="checkFlag" value="%{checkFlag}" />
		<s:submit type="image" src="../images/AddToCart.jpg" align="center"	action="addToCart" onclick="javascript:closeWindow()" />
		<%-- <s:submit type="button" value="ADD TO CART" align="center"
			action="addToCart" /> --%>

	</s:form>
	<br>
	<br>
	<br>
	<center><a href="javascript:windowClose()" >Close Window</a></center>
</body>

<script type="text/javascript">
	function closeWindow() {
		var x = document.getElementById("checkFlag").value;
		//alert("Radio "+document.getElementById("chooseSize").value);

		if (null != x && x == "Y") {
			window.close();
			window.opener.location = 'pizzaList.action';//'myaction.action?amount='+varAmount
		}

	}
</script>
<script type="text/javascript">
	function windowClose() {
			window.close();
			window.opener.location = 'pizzaList.action';//'myaction.action?amount='+varAmount

	}
</script>
</html>
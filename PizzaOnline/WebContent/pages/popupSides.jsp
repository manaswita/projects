<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body onload="javascript:closeWindow();">

	<s:form method="post"
		onsubmit="javascript:validateForm();">

		<h2 align="center">Select your topping , from the wide variety
			that we fling!</h2>
		<table align="center">

			<tr class="regtxt">

				<th>Select</th>

				<th align="left">Name</th>

				<th align="left">Type</th>

				<th>Price</th>

			</tr>

			<s:iterator value="sideToppingList">

				<tr>

					<td><s:checkbox name="selectSidesTopping" fieldValue="%{name}"
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
		<font color="Red">	<s:actionerror/></font>
			<tr>
				<td class="regtxt"><s:select name="selectQuantity"
						list="quantity" label="Select the number of Items" headerKey="-1"
						headerValue="Select Number" cssClass="regtxt" /></td>
			</tr>
		</table>
		<%
String sideName=request.getParameter("sideName");

%>
		<s:hidden name="sideName" value="%{sideName}" />
		<s:hidden id="checkFlag" name="checkFlag" value="%{checkFlag}" />

		<s:submit type="image"  src="../images/AddToCart.jpg" value="ADD TO CART" align="center"
			action="addToCartSides" />

	</s:form><br>
	<br>
	<br>
	<br>
	<br>
<center><a href="javascript:windowClose()" >Close Window</a></center>
</body>

<script type="text/javascript">
function closeWindow(){
	var x=document.getElementById("checkFlag").value;
	//alert(x);
	if(null!=x && x=="Y"){
		window.close();
		window.opener.location = 'sideList.action';//'myaction.action?amount='+varAmount
	}

}
function windowClose() {
	window.close();
	window.opener.location = 'sideList.action';//'myaction.action?amount='+varAmount

}


</script>
</html>
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
<h1 align="center">please select your quantity and size</h1>
<h2 align="center">Tasty food always...good!</h2>
	<s:form  action="getTopping" method="post"
		onsubmit="javascript:validateForm();">

		
		<br>

		<table>
			<tr height="20"></tr>
			<tr>
				<td class="regtxt"><s:checkboxlist list="chooseSize"
						name="chooseSize" value="defaultSize" label="Choose your size" />
				</td>
			</tr>
			<font color="Red"><s:actionerror/></font>
			<tr>
				<td class="regtxt"><s:select name="selectQuantity"
						list="quantity" label="Select the number of Items" headerKey="-1"
						headerValue="Select Number" cssClass="regtxt" /></td>
			</tr>
		</table>
		<%
	String dbeverageName=request.getParameter("beverageName");

%>
		<s:hidden name="beverageName" value="%{beverageName}" />
		<s:hidden id="checkFlag" name="checkFlag" value="%{checkFlag}" />

		<s:submit type="image"  src="../images/AddToCart.jpg" value="ADD TO CART" align="center"
			action="addToCartBeverage" />

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
		window.opener.location = 'beverageList.action';//'myaction.action?amount='+varAmount
	}
}

function windowClose() {
	window.close();
	window.opener.location = 'beverageList.action';//'myaction.action?amount='+varAmount

}
</script>
</html>
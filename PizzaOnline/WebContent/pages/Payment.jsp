<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.impetus.pizzaonline.model.User"%>
<html>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
</head>

<body onload="javascript:hide()">


<%
	User user = new User();
	user = (User) session.getAttribute("getUser");
	String role = user.getRole();
%>
<%
		if (role.equals("Customer")) {
	%>
	<h1 align=center>PAYMENT</h1>
	
	<s:form  name="paymentForm" action="order" >
	<center><font color="Red"><s:actionerror /></font></center>
	<s:radio  name="mode"  label="Choose Payment Mode" list="{'Cash On Delivery','Debit Card'}" onclick="javascript:showPayment()"  />
	<s:radio  name="offer"  label="Choose Offer" list="%{populateList}" value="Choose Offer" onclick="javascript:show()" />
	<s:textfield id="coupon" name="couponNumber" label="Enter Coupon Number"  />
	
	
	
	<h2 align="center">PLEASE SELECT YOUR BANK</h2>
	
	
		<table border="1" width="100%" align="center">

			<tr>
				<s:select name="bank" list="bankList" label="Select theBanks"
					headerKey="-1" headerValue="Select Bank" cssClass="regtxt" />
			</tr>

		</table>
		<br>
		<br>
		<br>

		<table border="1" cellpadding="2" cellspacing="2" width="100%"
			align="center">
			
				<%-- <s:radio  name="offer"  label="Choose Offer" list="%{populateList}" value="Choose Offer" onclick="javascript:show()" />
				<s:textfield id="coupon" name="couponNumber" label="Enter Coupon Number"  /> --%>
			
				<s:textfield id="cardNo" name="cardNumber" label="Card Number" value="1111111111111111"/>
			
				<s:textfield name="name" label="Name On Card" value="enter name"/>
			
			
				<s:select name="selectMonth" list="monthList" label="month"
						headerKey="-1" headerValue="Select Month" cssClass="regtxt" />
				<s:select name="selectYear" list="yearList" label="year"
						headerKey="-1" headerValue="Select Year" cssClass="regtxt" />
			
			
				<s:textfield name="cvv" label="CVV" value="111" />
			
		</table>
<table border="1" cellpadding="2" cellspacing="2" width="100%"
			align="center">
			
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
<s:textfield name="area" label="area"/>
</tr>
<tr> 
 	<s:textfield name="city" label="City"/>
</tr>
<tr>
<s:textfield name="pin" label = "Pincode"/>
</tr>
<tr>
<s:textfield name="mobile" label="Mobile No"/>
</tr>
			
		</table>
		<%-- <center><a href="<s:url action='order.action'/>"><img src="../images/ContinueToPayment.jpg"></a></center> --%>
		<s:submit type="image" src="../images/ContinueToPayment.jpg" align="center" action="order" />



	</s:form>
	
	<%
			} else {
		%>
		<s:form name="paymentForm" action="order" >
		<center><font color="Red"><s:actionerror /></font></center>
			<table>
			<s:radio  name="mode"  label="Choose Payment Mode" list="{'Cash On Delivery'}" value="Cash On Delivery"  />
				<s:radio  name="offer"  label="Choose Offer" list="%{populateList}" value="Choose Offer" onclick="javascript:show()" />
				<s:textfield id="coupon" name="couponNumber" label="Enter Coupon Number"  />
				<s:textfield id="cardNo" name="cardNumber" label="Card Number" value="1111111111111111" disabled="true" />
				<s:textfield name="name" label="Name On Card" value="BPO" disabled="true" />
				<s:textfield name="cvv" label="CVV" value="111" disabled="true" />
				<s:submit type="image" src="../images/ContinueToPayment.jpg" align="center" action="order" />
			</table>
		</s:form>
			
			<%
			}
	%>
</body>
<script type="text/javascript">
	function validateDetails() {
		var cardNo = document.getElementById("cardNo").value;
		//alert(isNaN(cardNo));
		if (null == cardNo) {
			alert("Card Field cannot be null");
			return false;
		} else if (cardNo.length < 16) {
			alert("Card No has to be 16 digits");
			return false;
		} else if (isNaN(cardNo)) {
			alert("Card No can be only numbers");
			return false;
		} else {
			return true;
		}
		
	}
</script>

<script type="text/javascript">

function show(){
	
if(document.getElementById('order_offerNO OFFER').checked==false){

	document.getElementById('coupon').style.display="block"; //for show
}else{
	hide();
}
	
}
function hide(){
	 
	document.getElementById('coupon').style.display="none"; //for hide
	/* document.getElementById('offer').style.display="block"; */ 
}

function showPayment(){
	
	if(document.getElementById('order_modeCash On Delivery').checked==false){

		document.getElementById('order_bank').style.display="block";
		document.getElementById('cardNo').style.display="block";
		document.getElementById('order_name').style.display="block";
		document.getElementById('order_selectMonth').style.display="block";
		document.getElementById('order_selectYear').style.display="block";
		document.getElementById('order_cvv').style.display="block";
		document.getElementById('order_flat_no').style.display="block";
		document.getElementById('order_bldg_name').style.display="block";
		document.getElementById('order_lane').style.display="block"; 
		document.getElementById('order_area').style.display="block"; 
		document.getElementById('order_city').style.display="block";
		document.getElementById('order_pin').style.display="block";
		
	}
	else{
		
		document.getElementById('order_bank').style.display="none";
		document.getElementById('cardNo').style.display="none";
		document.getElementById('order_name').style.display="none";
		document.getElementById('order_selectMonth').style.display="none";
		document.getElementById('order_selectYear').style.display="none";
		document.getElementById('order_cvv').style.display="none";
		document.getElementById('order_flat_no').style.display="none";
		document.getElementById('order_bldg_name').style.display="none";
		document.getElementById('order_lane').style.display="none"; 
		document.getElementById('order_area').style.display="none"; 
		document.getElementById('order_city').style.display="none";
		document.getElementById('order_pin').style.display="none";
		
	}
}

</script>
</html>
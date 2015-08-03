<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.impetus.pizzaonline.model.User"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style1.css" type="text/css" />

<title>Header</title>
</head>
<body>
<%
	User user = new User();
	User custUser = new User();
	user = (User) session.getAttribute("getUser");
	custUser = (User) session.getAttribute("customer");
	String role = user.getRole();
	String name=user.getName();
	String cust = "";
	if(custUser!=null){
	 cust = custUser.getName();
	}
%>

User Logged In:  <%=name%><br>
	<%
		if (role.equals("Customer")) {
	%>
	<div id="main">

  <div class="sub_main">

    <div class="header"> <center><img src="../images/banner.png"></center></div>

    <div class="menublock">

      <ul>
	
	<li><a href="<s:url action='home.action'/>">Home</a></li>
	<li><a href="../pages/Contact Us.jsp">Contact Us</a></li>
	<li><a href="../pages/Locations.jsp">Locations</a></li>
	<!-- <li><a href="../pages/AboutUs.jsp">About Us</a></li> -->
	<li><a href="<s:url action='userHistory.action'/>">Previous orders</a></li>
	<li><a href="<s:url action='editProfile.action'/>">Edit Profile</a></li>
	<li><a href="<s:url action='includeOffer.action'/>">Hot Deals</a></li>
	<li><a href="<s:url action='logout.action'/>">LogOut</a> </li>
	
	</ul>

    </div>

   
    </div> 
    </div>
    
	
	<%
		} else if (role.equals("BPO")) {
			
	%>
	
	Customer Logged In:  <%=cust%><br>
	<div id="main">

 	 <div class="sub_main">

    <div class="header"> <center><img src="../images/banner.png"></center></div>

    <div class="menublock">

      <ul>
	<li><a href="<s:url action='home.action'/>">Home</a></li>
	<li><a href="<s:url action='userHistory.action'/>">View Previous orders</a></li>
	<li><a href="/PizzaOnline/pages/BPORegisterUser.jsp">Register User</a></li>
	<li><a href="<s:url action='includeOffer.action'/>">Hot Deals</a></li>
	<li><a href="<s:url action='logout.action'/>">LogOut</a></li>
	</ul>

    </div>

   
    </div> 
    </div>
	<%
			} else {
		%>
	<div id="main">

 	 <div class="sub_main">

    <div class="header"> <center><img src="../images/banner.png"></center></div>

    <div class="menublock">
    <ul>
	<li><a href="<s:url action='home.action'/>">Home</a></li>
	<li><a href="<s:url action='populateStaff.action'/>">Add/RmvStaff</a></li>
	<li><a href="<s:url action='populateSize.action'/>">Add/RmvItem</a></li>
	<li><a href="<s:url action='populateDays.action'/>">Add/RmvOffer</a></li>
	<li><a href="<s:url action='custHistory.action'/>"> OrderHistory</a></li>
	<li><a href="<s:url action='includeOffer.action'/>">HotDeals</a></li>
	<li><a href="<s:url action='logout.action'/>">LogOut</a></li>
	</ul>

    </div>

   
    </div> 
    </div>
	<%
			}
	%>
	
	
	
</body>
</html>
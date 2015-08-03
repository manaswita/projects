<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag-12.tld" prefix="display"%>
<%@ page language="java" import="com.impetus.pizzaonline.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<title>Customer History</title>
</head>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<body>
<h1 align=center>YOUR ORDER HISTORY</h1>

	<s:form action="custHistory">

		<table  ALIGN="CENTER" BORDER="1" BGCOLOR="Brown" CELLPADDING="2" CELLSPACING="2" height="100">
			<TR>
				<TD ALIGN="CENTER" height="1" bgcolor="Green">
					<p align="center">
						<b><font style="font-family: Verdana; font-size: 10-PT"
							face="Verdana" size="2" color="White">Customer Order
								History</font></b>
					</p>
				</TD>
			</TR>
			<TR>
				<TD height="110" valign="top" align="left"><display:table 
						 cellspacing="1" cellpadding="1"  id="row" border="1"
						name="historyList" pagesize="10"
						requestURI="/pages/custHistory.action">
					
						<display:column title="ItemNames" sortable="true"  align="center"
							property="itemNames"></display:column>
							
						<display:column title="TotalItems" sortable="true"
							property="totalItems" align="center"></display:column>
						<display:column title="OrderDate"  property="orderDate" align="center"></display:column>
						<display:column title="TotalAmount" sortable="true" 
							property="totalAmount" align="center"></display:column>

						<display:setProperty name="paging.banner.placement" value="bottom"  />
					</display:table>
					</TD>
			</TR>
		</table>
	</s:form>
</body>
</html>
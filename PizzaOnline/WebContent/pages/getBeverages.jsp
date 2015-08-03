<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.impetus.pizzaonline.model.ItemModel"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/style.css" type="text/css">
<title>Insert title here</title>
</head>
<body>
<center><img src=../images/beverageHeading.png></center>
<h2 align="center">let urself chill,to let go the heat that kill..</h2>
<script type="text/javascript">
function submitform(value)
{
	
    var url='bevToppingList.action?beverageName='+value;
    window.open(url,"myWindow","width=700, height=870,top=0,left=0");
}
</script>
<s:form >
		<s:if test="%{items.isEmpty()}">0</s:if>
	<s:else>
	<%
			Integer cartSize=(Integer) request.getAttribute("cartSize");
			String cartString="My Cart ("+cartSize+")";
			//System.out.println("cartSize :"+cartString);
		%>
		<table align="center" width="70%" >
	<tr>
	<%if  (null== cartSize  || cartSize==0){%>
	<td> MyCart(0)</td><%}else{ %>
		<td><a href="<s:url action='cart.action'/>"
			class="titleText_dkOrange"><%=cartString%></a></td>
			<%} %>
	</tr>
	</table>
	<table border="0" cellpadding="5" cellspacing="5" width="100%">
	<tr>
		<th>NAME</th>
		<th>TYPE</th>
		<th>PRICE</th>
		<th>DESCRIPTION</th>
	</tr>
	 <s:iterator value="items">
	 <tr class="regtxt">
		 	
                <td class="regtxtLegals a:link"><a href="#" onclick="submitform('<s:property value="key" />')"><s:property value="key" />
                <%
  			   				String key1 = (String) request.getAttribute("key");
  			   				key1=key1.replace(" ", "");
			                String imageurl="../images/" + key1 + ".gif";
			                %>
                <img src=<%=imageurl%>>
                
                
                </a></td>
           
                <s:iterator value="value">
                 	<td >
                		<s:if test="%{type=='VEGETARIAN'}">
            				<img src="../images/Veg_heading.gif" >
            			</s:if>
          			  <s:if test="%{type=='NONVEGETARIAN'}">
            				<img src="../images/Nonveg_heading.gif" >
          			  </s:if>
                	</td>
               		 <td class="inputHeaders">
                  	 	<s:property value="values[0]"/>
                  	 </td>
                                
                	<td class="inputHeaders">
                		<s:property value="description" />
                	</td>
                </s:iterator>
   		</s:iterator>
	  	   		
    	    		
    	</table>
    		</s:else>
		<%
		List<ItemModel> cartList= (List<ItemModel>)session.getAttribute("cartList");
		Integer cartSize=0;
		System.out.println(cartSize);
		if(cartList!=null && cartList.size()!=0){
		 cartSize=cartList.size();
		
		}
	%>
		<table align="center">
	<tr>
	<%if  (null== cartSize || cartSize==0){%>
	
	<td align="center"></td>
	
	<%}else{ %>
	
		<td align="center"><a href="<s:url action='cart.action'/>"><img
				src="../images/ContinueToPayment.jpg"></a></td>
	
			<%} %>
	</tr>
</table>
</s:form>
</body>
</html>
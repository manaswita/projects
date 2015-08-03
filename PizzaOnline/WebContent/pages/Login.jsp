<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<script>
function submitForm(){
	document.form.action='/PizzaOnline/pages/loginUser.action';
	document.form.submit();
}
</script>
<head>
<link rel="stylesheet" href="./css/style.css" type="text/css">

<title>Struts 2 - Login Application</title>
<style type="text/css">
h1 {font-size:250%;}
h2{font-size:150%}

</style>
</head>
<body >
	<br>
	<br>
	<center> <div class="imgs_contentblock"> <img src="images/pizza-hut-double-deep-pizza-730704.jpg"  alt=""/> <img src="images/pizza1.jpg" alt=""/> <img src="images/pizza2.jpg" alt=""/><img src="images/pizza-hut-double-deep-pizza-730704.jpg"  alt=""/> <img src="images/pizza1.jpg" alt=""/> <img src="images/pizza2.jpg" alt=""/> <img src="images/pizza3.jpg" alt="" /> </div></center><br>
	
	<center><img align="top" src="./images/welcome.png"></center><br>
	<center><img align="top" src="./images/PizzaLogo.png"></center>
	<center><img  src="./images/logo.png"></center>
	<h2 align="center"><font color="Green">Where Food is always so good!!!	</font></h2>
	<br>
	<h2 align="center">Please Login</h2>
	<center>
		<font color="Red"><s:actionerror /></font>
	</center>
	
	<s:form name="form" action="loginUser" method="post">
	
		<table align="center">

			<s:radio name="delivery" label="Select "
					list="{'Pick Up','Home Delivery'}" />

			<tr>
				<s:textfield id="username" name="loginName" label="username"
					size="20" />
			</tr>
			<tr>
				<s:password id="password" name="password" label="password" size="20" />
			</tr>
			<tr>
			<td><s:submit type="image" onClick="submitForm()" src="images/submit_button.gif" align="center"/></td>
			
			
			</tr>
		</table>
		<center><a href="/PizzaOnline/pages/ForgotPassword.jsp" >Forgot password</a></center>
			<br>
		<h3 align="center">NOT SIGNED UP??? PLEASE CLICK ON THIS!!!</h3>
		
			<center>	<a href="<s:url action='populateUser.action'/>"><img
				src="./images/signup.jpg" ></a></center>
		

	</s:form>

</body>




</html>
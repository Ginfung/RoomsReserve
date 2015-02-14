<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	 <link href="css/customStyle.css" rel="stylesheet">

   
  </head>
  <body>
   <div id="fullBg"></div>

<div class="container">
  <form class="form-signin customSignIn" action="LoginPageServlet" method="get">          
      <h1 class="form-signin-heading"></h1>
      <input id="loginId" name="loginId" type="text" class="form-control"  placeholder="University Login ID" required="" autofocus="" />
      <input id="password" name="password" type="password" class="form-control"  placeholder="Password" required=""/>     
      
      <button id="loginButton" class="btn btn-lg btn-primary btn-block indexPageLoginButton" type="submit">Log in</button>   
    </form>
<div><p style="color:red;margin-left: 411px;margin-top: -10px;font: bold;font-weight: 700;"><i>Invaid UserId or Password. Please Try Again.</i></div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	
      
</div>	
  </body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Welcome</title>
</head>
<body background='Back.jpg'>

<div class="row">
<div class="col-sm-4"> </div>
<div class="col-sm-4"><h1 style="text-align: center;">Welcome</h1></div>
<div class="col-sm-4"> </div>
</div>
<br><Br>

<div class="row">

<div class="col-sm-2"> </div>
<a href="openLoginView.do" ><div class="jumbotron col-sm-3" style="border-width:10px;padding:20px;border-radius:10px;background-color:#00CCFF;" ><h2 style="text-align: center;"><span class="glyphicon glyphicon-log-in"></span>&nbsp Login</h2></div></a>
<div class="col-sm-2"> </div>
<a href="openRegisterView.do" ><div class="jumbotron col-sm-3" style="border-width:10px;padding:20px;border-radius:10px;background-color:#FFCC66;"><h2 style="text-align: center;"><span class="glyphicon glyphicon-user"></span>&nbsp Register</h2></div></a>
<div class="col-sm-2"> </div>
</div>

<br>
 
<%  
if(request.getAttribute("message")!=null){%>

<div class="alert alert-info">
    <a class="close" data-dismiss="alert" aria-label="close">&times;</a>
<%
out.print(request.getAttribute("message"));

}
%> 
  </div>

</body>
</html>
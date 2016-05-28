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
<title>Login</title>
</head>
<body background='Back.jpg'>
<h2 style="text-align: center;">Enter username and password to login</h2>

 
 <form class="form-horizontal" role="form" action='Log.jsp' method='post'>
    <div class="form-group">
      <label class="control-label col-sm-2" for="eid">Id:</label>
      <div class="col-sm-3">
        <input type="text" class="form-control" id="eid" placeholder="eid" name="eid">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pass">Password:</label>
      <div class="col-sm-3">
        <input type="password" class="form-control" id="pass" placeholder="pass" name="pass">
      </div>
    </div>
     <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-5">
        <button type="submit" class="btn btn-success">Submit</button>
      </div>
    </div>
  </form>
 

<%if(request.getAttribute("errorMessage")!=null){%>
<div class="alert alert-warning">
<%	out.print(request.getAttribute("errorMessage"));}
	%>
	</div>
</body>
</html>
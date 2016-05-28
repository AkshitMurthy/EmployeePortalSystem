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
<title>Register</title>
</head>
<body background="Back.jpg">
<div class="container">
<h1 style="text-align:center;">Please Register</h1>

 <form class="form-horizontal" role="form" action='Reg.jsp' method='post'>
    <div class="form-group">
      <label class="control-label col-sm-2" for="name">First Name:</label>
      <div class="col-sm-7">
        <input type="text" class="form-control" id="name" placeholder="" name="name">
      </div>
    </div>
    
     <div class="form-group">
      <label class="control-label col-sm-2" for="lname">Last Name:</label>
      <div class="col-sm-7">          
        <input type="text" class="form-control" name="lname" id="lname" placeholder="">
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="eid">Employee ID:</label>
      <div class="col-sm-7">          
        <input type="text" class="form-control" name="eid" id="eid" placeholder="eg. 123">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="phone">Phone Number:</label>
      <div class="col-sm-7">
        <input type="text" name="phone" class="form-control" id="phone" placeholder="">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="address">Address:</label>
      <div class="col-sm-7">
        <input type="text" name="address" class="form-control" id="address" placeholder="">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email id:</label>
      <div class="col-sm-7">
        <input type="email" name="email" class="form-control" id="email" placeholder="eg. a@b.com">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="dob">Date of Birth:</label>
      <div class="col-sm-7">
        <input type="text" name="dob" class="form-control" id="dob" placeholder="dd/mm/yyyy">
      </div>
    </div>
    
    <div class="form-group">
    <label class="control-label col-sm-2" for="desig">Designation:</label>
    <div class="col-sm-10" style="width: 58.333333%;">
      <select class="form-control" name="desig" id="desig">
        <option value='Manager'>Manager</option>
        <option value='Engineer'>Engineer</option>
        <option value='Senior Engineer'>Senior Engineer</option>
      </select>
     </div> 
     </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pass">Password:</label>
      <div class="col-sm-7">
        <input type="password" name="pass" class="form-control" id="pass" placeholder="">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="repass">Retype Password:</label>
      <div class="col-sm-7">
        <input type="password" name="repass" class="form-control" id="repass" placeholder="">
      </div>
    </div>
    
    
    
    </div>


    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-success">Submit</button>
      </div>
    </div>
  </form>


<div class=alert alert-warning>
<%  
if(request.getAttribute("errorMessage")!=null)
	out.print(request.getAttribute("errorMessage"));
%></div>
</div>
</body>
</html>
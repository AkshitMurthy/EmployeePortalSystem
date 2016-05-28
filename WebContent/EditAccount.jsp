<%@page import="main.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="main.*"%>
    <%@page import="java.text.DateFormat"%>
    <%@ page import="java.io.*"%>
    <%@page import ="java.util.*" %>
<%@page import ="javax.servlet.*"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%HttpSession s=request.getSession(false);
RegBean rb=(RegBean)s.getAttribute("RBUSER");
String name=rb.getName();
String lname=rb.getLname();
String Email=rb.getEmail();
String Pass=rb.getPass();
String Repass=rb.getRepass();
String id=rb.getEid();
String phone=rb.getPhone();
String addr=rb.getAddress();
String dob=rb.getDob();
String desig=rb.getDesig();

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Edit Account</title>
</head>
<body background="Back.jpg">

<%
s=request.getSession(false);
LogBean lb=null;
String UName="";
if(s!=null)
{
	lb=(LogBean)s.getAttribute("USER");
	if(lb!=null)
		UName=lb.getName();
}
%>

 <nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
   
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <div  class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="Menu.do"><span class="glyphicon glyphicon-home"></span>Home</a></li>
        <li><a href="viewSearch.do">Search</a></li>
        <li><a href="viewNewTask.do">Allot Task</a></li>
        <li><a href="viewCheckTask.do">Check Task Status</a></li>
        <li><a href="viewAllTasks.do">View All Tasks</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"><%=UName%><span class="caret"></span></a>
      		<ul class="dropdown-menu">
      			<li><a href="viewEditAccount.do">Edit Account</a>
      			<li><a href="Logout.do">Logout <span class="glyphicon glyphicon-log-out"></span></a>
      		</ul>
      	</li>
        
      </ul>
    </div>
  </div>
</nav>


<br><br><br>

<h5>
<div class="container" style="text-align:left;float:left;">
<img src="clock.jpg" width=17 height=17>:
<%Date d= new Date();
DateFormat df= DateFormat.getDateTimeInstance();
out.println(df.format(d)); %>
</div>
</h5>

<br>
<h3 style="text-align:center; float:center;">Edit Account</h3>

<form style="float:center;" class="form-horizontal" role="form" action='EditAccountInt.jsp' method='post'>
    <div class="form-group">
      <label class="control-label col-sm-2" for="name">First Name:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="name" value="<%=name%>" name="name" style="width:300px">
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="lname">Last Name:</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="lname" value=<%=lname%> name="lname" style="width:300px">
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="eid">Employee ID:</label>
      <div class="col-sm-10">          
        <input type="text" class="form-control" name="eid" id="eid"  value=<%=id%> style="width:300px">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="phone">Phone Number:</label>
      <div class="col-sm-10">
        <input type="text" name="phone" class="form-control" id="phone"  value=<%=phone%> style="width:300px">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="address">Address:</label>
      <div class="col-sm-10">
        <input type="text" name="address" class="form-control" id="address"  value="<%=addr %>" style="width:300px">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Email id:</label>
      <div class="col-sm-10">
        <input type="email" name="email" class="form-control" id="email"  value=<%=Email%> style="width:300px">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="dob">Date of Birth:</label>
      <div class="col-sm-10">
        <input type="text" name="dob" class="form-control" id="dob"  value=<%=dob%> style="width:300px">
      </div>
    </div>
    
    <div class="form-group">
    <label class="control-label col-sm-2" for="desig">Designation:</label>
    <div class="col-sm-10">
      <select class="form-control" style="width:27%;" name="desig" id="desig" value=<%=desig%>>
        <option value='Manager'>Manager</option>
        <option value='Engineer'>Engineer</option>
        <option value='Senior Engineer'>Senior Engineer</option>
      </select>
     </div> 
     </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pass">Password:</label>
      <div class="col-sm-10">
        <input type="password" name="pass" class="form-control" id="pass"  value=<%=Pass%> style="width:300px">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="repass">Retype Password:</label>
      <div class="col-sm-10">
        <input type="password" name="repass" class="form-control" id="repass"  value=<%=Pass%> style="width:300px">
      </div>
    </div>
    </div>


    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-success">Submit</button>
      </div>
    </div>
  </form>
<%
if(request.getAttribute("errorMessage")!=null)
	out.print(request.getAttribute("errorMessage"));
%>
</body>
</html>
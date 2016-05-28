<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="main.*"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Search</title>
<style>

</style>
</head>
<body background="Back.jpg">

<%
HttpSession s=request.getSession(false);
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
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="Menu.do"><span class="glyphicon glyphicon-home"></span>Home</a></li>
        <li class="active"><a href="viewSearch.do">Search</a></li>
        <li><a href="viewNewTask.do">Allot Task</a></li>
        <li><a href="viewCheckTask.do">Check Task Status</a></li>
        <li><a href="viewAllTasks.do">View All Tasks</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown"><%=UName%><span class="caret"></span></a>
      		<ul class="dropdown-menu">
      			<li><a href="viewEditAccount.do">Edit Account<span class="glyphicon glyphicon-user"></span></a>
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
<h3 style="text-align:center; float:center;">Search</h3>

<form class="form-vertical" role="form" action='search.do' method='post'>
    
    <div class="col-sm-3" id="desigdrop">
    <label for="sel1">Search By:</label>
      <select class="form-control" name="searchParam">
        <option value='name'>Name</option>
        <option value='id'>Id</option>
        <option value='email'>Email</option>
        <option value='phone'>Phone</option>
      </select>
     </div>
      
    <div class="form-group">
      <label class="control-label col-sm-4" for="param">Enter Parameter:</label><br>
      <div class="col-sm-5">
        <input type="text" class="form-control" id="param" placeholder="name/id/phone/email" name="param">
      </div>
    </div>
         <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-5">
        <button type="submit" class="btn btn-success">Submit</button>
      </div>
    </div>
  </form>

<%
if(request.getAttribute("searched")!=null)
{
	ArrayList<RegBean> al=(ArrayList<RegBean>)request.getAttribute("searched");
	out.print("<h3> Matches found in </h3>");
	out.print("<table class='table' border=1 style="+"width:100%;border-collapse:collapse;"+">");
	out.print("<thead><tr>");
	out.print("<th> SL No  </th><th> ID  </th><th> Name </th><th> Email Id </th><th> Phone Number </th><th> Address </th>");
	out.print("</tr></thead>");
	int uid=0;
	for(RegBean rb:al)
	{
		uid++;
		out.print("<tr>");
		out.print("<td>"+uid+" </td><td> "+rb.getEid()+" </td><td> "+rb.getName()+" </td><td> "+rb.getEmail()+" </td><td> "+rb.getPhone()+" </td><td> "+rb.getAddress()+" </td>");
		out.print("</tr>");
	}
	out.print("</table>");
	uid=0;
	out.print("<hr>");
	
}
if(request.getAttribute("errorMessage")!=null)
{
	out.print("<div class='alert alert-warning'>");
	out.println("<h3>"+request.getAttribute("errorMessage"));	
	out.print("</h3></div>");
}
%>
</body>
</html>
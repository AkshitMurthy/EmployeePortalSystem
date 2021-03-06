<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="main.LogBean"%>
    <%@page import="java.text.DateFormat"%>
    <%@ page import="java.io.*"%>
    <%@page import ="java.util.*" %>
<%@page import ="javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>New Task</title>
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
        <li><a href="viewSearch.do">Search</a></li>
        <li class="active"><a href="viewNewTask.do">Allot Task</a></li>
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
<%if(request.getAttribute("message")==null){ %>
<h3 style="text-align:center; float:center;">Allot Task</h3>


<form class="form-horizontal" role="form" action="Task.jsp" method="post">

<div class="form-group">
	<label class="control-label col-sm-2" for="eid">ID:</label>
	<div class="col-sm-3">
		<input class="form-control" type="text" id="eid" name="eid" placeholder="ID">
	</div>
</div>

<div class="form-group">
	<label class="control-label col-sm-2" for="task">Task:</label>
	<div class="col-sm-3">
		<input class="form-control" type="text" id="task" name="task" placeholder="task">
	</div>
</div>

<div class="form-group">
	<div class="col-sm-offset-2 col-sm-5">
		<button type="submit" class="btn btn-success">Allot</button>
	</div>
</div>

</form>

<%
if(request.getAttribute("errorMessage")!=null)
	out.print(request.getAttribute("errorMessage"));
%>

<%
}
else
{
	out.println("<h3 style='text-align:center; float:center;'>Dear user, you do not yet have authority to assign tasks</h3>");
}
%>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="main.*"%>
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
<title>All Tasks</title>
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
        <li><a href="viewNewTask.do">Allot Task</a></li>
        <li><a href="viewCheckTask.do">Check Task Status</a></li>
        <li class="active"><a href="viewAllTasks.do">View All Tasks</a></li>
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
<h3 style="text-align:center; float:center;">Tasks</h3>

<%
if(!(s.getAttribute("message").equals("")))
{
	int srno=1;
	List<TaskBean> l=(List<TaskBean>)s.getAttribute("tasks");
	ArrayList<TaskBean> al=(ArrayList<TaskBean>) l;
	out.print("<table class='table' border=1 style="+"text-align:center;"+"><thead><tr><th>No.<th>Id of the Employee</th></th><th>Task</th><th>Status</th></tr></thead>");
	for(TaskBean tb:al)
	{
		out.print("<tr><td>"+srno+"</td><td>"+tb.getEid()+"</td><td>"+tb.getTask()+"</td><td>"+tb.getDone()+"</td></tr>");	
		srno++;
	}
	out.print("</table><br><hr>");
}
else
{
	out.print("<h2>No tasks as of now!!</h2>");	
}
%>

</body>
</html>
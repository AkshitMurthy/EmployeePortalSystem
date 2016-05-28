<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*" %>
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
<title>Welcome</title>
</head>
<body background='Back.jpg'>

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
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("USER")==null)
   response.sendRedirect("LoginView.jsp");

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
        <li class="active"><a href="Menu.do"><span class="glyphicon glyphicon-home"></span>Home</a></li>
        <li><a href="viewSearch.do">Search</a></li>
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


<br><br><br><br>
<h1>
<div style="text-align:center;">
Welcome  
<%
out.print(" "+UName);
%>
</div>
</h1>


<h5>
<div class="container" style="text-align:left;float:left;">
<img src="clock.jpg" width=17 height=17>:
<%Date d= new Date();
DateFormat df= DateFormat.getDateTimeInstance();
out.println(df.format(d)); %>
</div>
</h5>

<br><br>
<h3><div style="text-align:center;" class="row";style="margin-right: -88px;margin-left: 28px; ">

<div class="col-sm-0.5"></div>

<a href="viewSearch.do">
<div class="jumbotron col-sm-3" style="height:125px;border-radius:10px;background-color:#FFCC66;">
Search<br><br><br></div></a>

<div class="col-sm-1"></div>

<a href="viewNewTask.do">
<div class="jumbotron col-sm-3" style="background-color:#00CCFF;height:125px;border-radius:10px;">
Allot Task<br><br><br></div></a>

<div class="col-sm-1"></div>

<a href="viewCheckTask.do">
<div class="jumbotron col-sm-3" style="background-color:#FFCC66;height:125px;border-radius:10px;">
Check Task Status<br><br><br></div></a>

<div class="col-sm-1"></div>

<div class="col-sm-2"></div>

<a href="viewAllTasks.do">
<div class="jumbotron col-sm-3" style="height:125px;border-radius:10px;background-color:#00CCFF;">
View All Tasks<br><br><br></div></a>

<div class="col-sm-1"></div>

<a href="viewEditAccount.do">
<div class="jumbotron col-sm-3" style="height:125px;background-color:#FFCC66;border-radius:10px;">
Edit Account<br><br><br></div></a>

<div class="col-sm-2"></div>

</div></h2>
<br>
<h3>
<div style="text-align: center;">
<%
response.setContentType("text/html");
String birthday=(String)s.getAttribute("birthday");
if(!s.equals("")){
	out.print("<div class='container'><img src='birth.jpg' height=40 width=40> ");
	out.println(birthday+"<img src='birth.jpg' height=40 width=40></div>");
	
}
%>
</div>
</h2>


</body>
</html>
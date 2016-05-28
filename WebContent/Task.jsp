<jsp:useBean id="task" class="main.TaskBean" scope="request"></jsp:useBean>
	<jsp:setProperty name="task" property="*"/> 
	<jsp:setProperty name="task" property="done" value="Pending"/>
<jsp:forward page="/allotTask.do"/>
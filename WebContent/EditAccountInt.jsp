<jsp:useBean id="updateBean" class="main.RegBean" scope="request"></jsp:useBean>
	<jsp:setProperty property="*" name="updateBean"/> 
<jsp:forward page="/updateRegister.do"/>
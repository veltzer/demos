<%@page contentType="text/html"%>
<%!
    String[] colors = {"","white","black","blue","red","yellow","green","purple"};
%>

<%--
   Enter your code here
--%>


<html>
<head>
<title>JSP Page</title>
</head>
<body bgcolor='<%=session.getAttribute("bgColor")%>'
	text='<%=session.getAttribute("fontColor")%>'>
	This is a test
	<form action='' method=POST>
		<h3>User preferences</h3>
		Background color: <select name='bgColor'>
			<%--
       Enter your code here
    --%>
		</select> <br> Font color: <select name='fontColor'>
			<%--
       Enter your code here
    --%>
		</select><br> <input type=submit value='change'>
	</form>

</body>
</html>

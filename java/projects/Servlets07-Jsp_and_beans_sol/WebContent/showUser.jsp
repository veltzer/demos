<%@page contentType="text/html" errorPage="error.jsp"%>
<jsp:useBean id="user" class="exercise.User" scope="session" />
<jsp:setProperty name="user" property="lname" />
<jsp:setProperty name="user" property="fname" />
<jsp:setProperty name="user" property="id" />
<jsp:setProperty name="user" property="email" />
<%
    user.validate();
%>
<html>
<head>
<title>User</title>
</head>
<body>
	<h3>The user details are:</h3>
	First Name:
	<jsp:getProperty name="user" property="fname" /><br> Last Name:
	<jsp:getProperty name="user" property="lname" /><br> ID:
	<jsp:getProperty name="user" property="id" /><br> Email:
	<jsp:getProperty name="user" property="email" /><br>
</body>
</html>
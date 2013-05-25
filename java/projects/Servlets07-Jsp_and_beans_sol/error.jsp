<%@page contentType="text/html" isErrorPage="true"%>
<html>
<head>
<title>Error</title>
</head>
<body>
	The Following error was found on the form:
	<br>
	<%= exception.getMessage() %>
</body>
</html>

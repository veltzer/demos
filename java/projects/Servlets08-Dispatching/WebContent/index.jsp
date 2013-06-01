<%@page contentType="text/html"%>
<html>
<head>
<title>View items</title>
</head>
<body>
	<H3>Select view</H3>
	<FORM action='servlet/exercise.ControllerServlet' method=POST>
		<INPUT type=RADIO name='mode' value='cat' checked>View as
		items list<br> <INPUT type=RADIO name='mode' value='item'>View
		as separate items<br> <INPUT type=SUBMIT value='View'>
	</FORM>

</body>
</html>

<%@page contentType="text/html"%>
<jsp:useBean id="tableHelper" scope="session"
	class="exercise.TableHelper" />

<html>
<head>
<title>Tables</title>
</head>
<body>
	<%
    String[][] table1 = {{"first","second","third"},{"one","two","three"}};
    tableHelper.setData(table1);
%>
	<jsp:setProperty name="tableHelper" property="bgcolor" value="green" />
	<jsp:getProperty name="tableHelper" property="html" />

	<%
    String[][] table2 = {{"name","String"},{"email","String"},{"age","int"},{"address","String"}};
    tableHelper.setData(table2);
%>
	<br>
	<jsp:setProperty name="tableHelper" property="bgcolor" value="red" />
	<jsp:getProperty name="tableHelper" property="html" />
</body>
</html>

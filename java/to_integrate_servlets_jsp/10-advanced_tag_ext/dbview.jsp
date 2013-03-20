<%@page contentType="text/html"%>
<%@taglib uri="/course/exercise.tld" prefix="ex" %>

<ex:openconnection driver="sun.jdbc.odbc.JdbcOdbcDriver" url="jdbc:odbc:CartServlet" user="sa" />

<html>
<head><title>Databas view</title></head>
<body>
<h3>The items table</h3>

<table border=1>
<ex:table tableName="items">
    <tr>
    <td><ex:row colName="id"/></td><td><ex:row colName="name"/></td><td><ex:row colName="price"/></td>
    </tr>
</ex:table>
</table>
Connection is: <%= connection.getCatalog() %>
</body>
</html>

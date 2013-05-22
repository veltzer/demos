<%@page import="java.util.*,java.text.*,exercise.Item"%>
<jsp:useBean id="items" type="java.util.Map" scope="application" />

<html>
<head><title>List view</title></head>
<body>
<table border=1>
<%
    Iterator it =items.values().iterator();

    while (it.hasNext()) {
        Item item = (Item) it.next();
%>
    <tr>
    <td><%= item.getItemId()%></td><td><%=item.getName()%></td><td><%=NumberFormat.getCurrencyInstance(Locale.US).format(item.getPrice()) %></td></tr>
    </tr>
<%
    }
%>
</table>

</body>
</html>

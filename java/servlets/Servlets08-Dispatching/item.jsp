<%@page import="java.util.*,java.text.*,exercise.Item"%>
<jsp:useBean id="items" type="java.util.Map" scope="application" />

<html>
<head><title>Items view</title></head>
<body>
<%
    Iterator it =items.values().iterator();

    while (it.hasNext()) {
        Item item = (Item) it.next();
%>
    <TABLE border=1 width='150'>
    <TR><TD>
    <H3>Item number <%= item.getItemId() %></H3>
    Item name: <%= item.getName() %><br>
    Price: <%= NumberFormat.getCurrencyInstance(Locale.US).format(item.getPrice()) %>
    </TD></TR>
    </TABLE>
<%
    }
%>


</body>
</html>

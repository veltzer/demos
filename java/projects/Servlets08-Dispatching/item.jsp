<%@page import="exercise.Item"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>

<jsp:useBean id="items" type="java.util.Map" scope="application" />

<html>
<head>
<title>Items view</title>
</head>
<body>
	<%
    Iterator<Item> it =items.values().iterator();

    while (it.hasNext()) {
        Item item = it.next();
%>
	<TABLE border=1 width='150'>
		<TR>
			<TD>
				<H3>
					Item number
					<%= item.getItemId() %></H3> Item name: <%= item.getName() %><br>
				Price: <%= NumberFormat.getCurrencyInstance(Locale.US).format(item.getPrice()) %>
			</TD>
		</TR>
	</TABLE>
	<%
    }
%>


</body>
</html>

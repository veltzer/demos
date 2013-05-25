<%@page
	import="java.util.Iterator,java.text.NumberFormat,java.util.Locale,exercise.Item"%>
<jsp:useBean id="items" "type="java.util.Map" scope="application" />

<html>
<head>
<title>List view</title>
</head>
<body>
	<table border=1>
		<%
	@SuppressWarnings("unchecked")
    Iterator<Item> it =items.values().iterator();

    while (it.hasNext()) {
        Item item = it.next();
%>
		<tr>
			<td><%= item.getItemId()%></td>
			<td><%=item.getName()%></td>
			<td><%=NumberFormat.getCurrencyInstance(Locale.US).format(item.getPrice()) %></td>
		</tr>
		<%
    }
%>
	</table>

</body>
</html>

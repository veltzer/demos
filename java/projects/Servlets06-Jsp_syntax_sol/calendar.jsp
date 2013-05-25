<%@page contentType="text/html"%>
<%@page import="java.util.Calendar"%>

<html>
<head>
<title>Calendar</title>
</head>
<body>
	<% Calendar now = Calendar.getInstance(); %>
	<h4>This is now:</h4>
	<table border=1>
		<tr>
			<td>Day Of Month:</td>
			<td><%= now.get(Calendar.DAY_OF_MONTH) %></td>
		</tr>
		<tr>
			<td>Month:</td>
			<td><%= now.get(Calendar.MONTH) %></td>
		</tr>
		<tr>
			<td>Year:</td>
			<td><%= now.get(Calendar.YEAR) %></td>
		</tr>
		<tr>
			<td>Day Of Week:</td>
			<td><%= now.get(Calendar.DAY_OF_WEEK) %></td>
		</tr>
		<tr>
			<td>Day Of Year:</td>
			<td><%= now.get(Calendar.DAY_OF_YEAR) %></td>
		</tr>

		<tr>
			<td>Hours:</td>
			<td><%= now.get(Calendar.HOUR_OF_DAY) %></td>
		</tr>
		<tr>
			<td>Minutes:</td>
			<td><%= now.get(Calendar.MINUTE) %></td>
		</tr>
		<tr>
			<td>Seconds:</td>
			<td><%= now.get(Calendar.SECOND) %></td>
		</tr>
		<tr>
			<td>Milliseconds:</td>
			<td><%= now.get(Calendar.MILLISECOND) %></td>
		</tr>
		<tr>
			<td>Timezone:</td>
			<td><%= now.getTimeZone().getDisplayName() %></td>
		</tr>
	</table>
</body>
</html>

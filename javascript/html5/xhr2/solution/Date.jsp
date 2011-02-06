<%@ page language="java" contentType="application/json; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%
response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache"); 
response.setDateHeader ("Expires", -1); 
// To catch all browsers with HTML, we should also add:
//<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE"> 
//<META HTTP-EQUIV="Expires" CONTENT="-1">
%><%java.util.Date now = new java.util.Date();%>
<% if (Math.random() > 0.3) { %>
	{{timestamp:<%=now.getTime()%>}}
<% } else { %>
	{
		'error':true, 
		'type':'java.lang.NullPointerException', 
		'timestamp':<%=now.getTime()%>, 
		message:'Randomly Generated Null'
	}
	<% response.setStatus(500); %>
<% } %>

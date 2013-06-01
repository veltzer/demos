<%@page contentType="text/html"%>
<%@page import="java.lang.String"%>
<%!
    String[] colors = {"","white","black","blue","red","yellow","green","purple"};
%>

<%
    if (session.getAttribute("bgColor")==null) {
        session.setAttribute("bgColor","white");
    }
    if (session.getAttribute("fontColor")==null) {
        session.setAttribute("fontColor","black");
    }


    String bgColor = request.getParameter("bgColor");
    String fontColor = request.getParameter("fontColor");
    if (bgColor!=null && !bgColor.equals("")) {
        session.setAttribute("bgColor",bgColor);
    }
    if (fontColor!=null && !fontColor.equals("")) {
        session.setAttribute("fontColor",fontColor);
    }

%>

<html>
<head>
<title>JSP Page</title>
</head>
<body bgcolor='<%=session.getAttribute("bgColor")%>'
	text='<%=session.getAttribute("fontColor")%>'>
	This is a test
	<form action='' method=POST>
		<h3>User preferences</h3>
		Background color: <select name='bgColor'>
			<% for (int i=0;i<colors.length;i++) { %>
			<option><%= colors[i]%>
				<% } %>
			
		</select> <br> Font color: <select name='fontColor'>
			<% for (int i=0;i<colors.length;i++) { %>
			<option><%= colors[i]%>
				<% } %>
			
		</select><br> <input type=submit value='change'>
	</form>

</body>
</html>

<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<HTML>
<TITLE>Expression Language Exercise</TITLE>

<BODY>
	<H1>Expression Language (EL) Exercise</H1>

	<H2>EL Literals and Operators</H2>
	<TABLE BORDER="1">
		<TR>
			<TH>Expression</TH>
			<TH>Value</TH>
		</TR>
		<TR>
			<TD>1==2</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
		<TR>
			<TD>1+2*3/4</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
		<TR>
			<TD>10%3 (mod)</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
		<TR>
			<TD>2>1&&"ab"&gt;"aaa"</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
		<TR>
			<TD>empty null && empty "" && !empty "a"</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
		<TR>
			<TD>true?1:2</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
	</TABLE>
	<P>
	<H2>EL Implicit Objects</H2>
	<TABLE BORDER="1">
		<TR>
			<TD>pageContext.servletContext.serverInfo</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
		<TR>
			<TD>pageContext.request.remoteHost</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
		<TR>
			<TD>pageContext.session</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
		<TR>
			<TD>colored line for each value of the "colors" parameter in the
				url query-string</TD>
			<TD><c:forEach var="c" items="${'fill-expression'}">
					<li><font color="${'fill-expression'}">This line is
							${"fill-expression"}</font>.
				</c:forEach></TD>
		</TR>
		<TR>
			<TD>HTTP request headers</TD>
			<TD><c:forEach var="hdr" items="${'fill-expression'}">
					<li>${"fill-expression"} = ${"fill-expression"}
				</c:forEach></TD>
		</TR>
		<TR>
			<TD>Cookies</TD>
			<TD><c:forEach var="c" items="${'fill-expression'}">
					<li>name ${"fill-expression"} <br>domain
						${"fill-expression"} <br>value ${"fill-expression"}
				</c:forEach></TD>
		</TR>
	</TABLE>
	<P>
	<H2>EL function</H2>
	<TABLE BORDER="1">
		<TR>
			<TH>Expression</TH>
			<TH>Value</TH>
		</TR>
		<TR>
			<TD>sin(11/7)</TD>
			<TD>${"fill-expression"}</TD>
		</TR>
	</TABLE>
</BODY>
</HTML>

<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="WEB-INF/app.tld" prefix="f"%>

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
			<TD>${1==2}</TD>
		</TR>
		<TR>
			<TD>1+2*3/4</TD>
			<TD>${1+2*3/4}</TD>
		</TR>
		<TR>
			<TD>10%3 (mod)</TD>
			<TD>${10%3}</TD>
		</TR>
		<TR>
			<TD>2>1&&"ab"&gt;"aaa"</TD>
			<TD>${10>2 && "z" gt "a"}</TD>
		</TR>
		<TR>
			<TD>empty null && empty "" && !empty "a"</TD>
			<TD>${empty null && empty "" && !empty "a"}</TD>
		</TR>
		<TR>
			<TD>true?1:2</TD>
			<TD>${true?1:2}</TD>
		</TR>
	</TABLE>
	<P>
	<H2>EL Implicit Objects</H2>
	<TABLE BORDER="1">
		<TR>
			<TD>pageContext.servletContext.serverInfo</TD>
			<TD>${pageContext.servletContext.serverInfo}</TD>
		</TR>
		<TR>
			<TD>pageContext.request.remoteHost</TD>
			<TD>${pageContext.request.remoteHost}</TD>
		</TR>
		<TR>
			<TD>pageContext.session</TD>
			<TD>${pageContext.session}</TD>
		</TR>
		<TR>
			<TD>colored line for each value of the "colors" parameter in the
				url query-string</TD>
			<TD><c:forEach var="c" items="${paramValues['colors']}">
					<li><font color="${c}">This line is ${c}</font>.
				</c:forEach></TD>
		</TR>
		<TR>
			<TD>HTTP request headers</TD>
			<TD><c:forEach var="hdr" items="${header}">
					<li>${hdr.key} = ${hdr.value}
				</c:forEach></TD>
		</TR>
		<TR>
			<TD>Cookies</TD>
			<TD><c:forEach var="c" items="${cookie}">
					<li>name ${c.key} <br>domain ${c.value.domain} <br>value
						${c.value.value}
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
			<TD>${f:sin(11/7)}</TD>
		</TR>
	</TABLE>
</BODY>
</HTML>

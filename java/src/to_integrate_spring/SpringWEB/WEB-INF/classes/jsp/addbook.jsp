

<%@page import="java.util.List"%>
<%@page import="interbit.bookstore.Book"%>
<html>
	<body>
		<form action="addBook.spring" method="post">
			
			Title: <input type="text" name="title"/><br>
			Author: <input type="text" name="author"/><br>
			Price: <input type="text" name="price"/><br>
			
			<br><br><input type="submit" value="Add"/>
		</form>
	</body>
</html>



<%@page import="java.util.List"%>
<%@page import="interbit.bookstore.Book"%>
<html>
	<body>
		<table border="1">
			<tr>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
			</tr>
			<%
				List<Book> books = (List)request.getAttribute("books");
				for (Book b : books)
				{
			%>
				<tr>
					<td><%=b.getTitle() %></td>
					<td><%=b.getAuthor() %></td>
					<td><%=b.getPrice() %></td>
				</tr>
			<%
				}
			%>
			
		</table>
		<p>
			<a href="addBook.spring">Add</a>
		</p>
	</body>
</html>

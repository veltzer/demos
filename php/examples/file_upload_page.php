<html>
	<body>
		<form action="file_upload_server.php" method="post" enctype="multipart/form-data">
			<input type="hidden" name="MAX_FILE_SIZE" value="3000000"/>
			<input type="file" name="upload"/>
			<input type="submit" value="Upload"/>
		</form>
	</body>
</html>

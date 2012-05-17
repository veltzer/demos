//the user Model we loaded in the last snippet:
user.set('name', 'Edward Spencer');

//tells the Proxy to save the Model. In this case it will perform a PUT request to /users/123 as this Model already has an id
user.save({
	success: function() {
		console.log('The User was updated');
	}
});

//tells the Proxy to destroy the Model. Performs a DELETE request to /users/123
user.destroy({
	success: function() {
		console.log('The User was destroyed!');
	}
});

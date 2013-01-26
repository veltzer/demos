Ext.define('User', {
	extend: 'Ext.data.Model',
	fields: ['id', 'name', 'email'],
	proxy: {
		type: 'rest',
		url : '/users'
	},
});

// lets create a model instance...
var user=Ext.create('User', {name: 'Ed Spencer', email: 'ed@sencha.com'});
// save it to the server side...
user.save(); //POST /users

// here is how we load from the server.

var User=Ext.ModelManager.getModel('User');

//Uses the configured RestProxy to make a GET request to /users/123
User.load(123, {
	success: function(user) {
		console.log(user.getId()); //logs 123
	}
});

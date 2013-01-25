Ext.define('User', {
	extend: 'Ext.data.Model',
	fields: [
		{name: 'name',  type: 'string'},
		{name: 'age',   type: 'int'},
		{name: 'phone', type: 'string'},
		{name: 'alive', type: 'boolean', defaultValue: true}
	],
	changeName: function() {
		var oldName = this.get('name');
		var newName = oldName + ' The Barbarian';
		this.set('name', newName);
	}
});
// lets use the newly created 'User' object...
var user=Ext.create('User', {
	name : 'Conan',
	age : 24,
	phone: '555-555-5555'
});
user.changeName();
user.get('name'); //returns 'Conan The Barbarian'

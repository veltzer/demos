Ext.define('MovieModel', {
	extend: 'Ext.data.Model',
	fields: [
		{name: 'id', type: 'number'},
		{name: 'name', type: 'string'},
		{name: 'length', type: 'auto'},
		{name: 'size', type: 'auto'},
	],
	idProperty: 'id',
});

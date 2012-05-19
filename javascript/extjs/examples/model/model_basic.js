Ext.define('User', {
	extend: 'Ext.data.Model',
	fields: [
		{name: 'name', type: 'string'},
		{name: 'age', type: 'int'},
		{name: 'phone', type: 'string'},
		{name: 'alive', type: 'boolean', defaultValue: true}
	],
});

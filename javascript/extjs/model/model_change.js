Ext.define('User', {
	extend: 'Ext.data.Model',
	fields: [
		{name: 'name', type: 'string',
			convert: function(value,record) {
				return record.get('name')+' the barbarian');
			}
		},
		{name: 'age', type: 'int'},
		{name: 'phone', type: 'string'},
		{name: 'alive', type: 'boolean', defaultValue: true}
	],
});

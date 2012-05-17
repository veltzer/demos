Ext.define('MyApp.data.MyModel', {
	extend: 'Ext.data.Model',
	requires: ['Ext.data.SequentialIdGenerator'],
	idgen: 'sequential',
	...
});

Ext.define('MyApp.data.MyModel', {
	extend: 'Ext.data.Model',
	requires: ['Ext.data.UuidGenerator'],
	idgen: 'uuid',
	...
});

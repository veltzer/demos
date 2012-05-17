var store = Ext.create('Ext.data.Store', {
	model: 'User',
	sorters: [
		{
			property : 'age',
			direction: 'DESC',
		},
		{
			property : 'firstName',
			direction: 'ASC',
		},
	],
});

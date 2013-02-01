var store=Ext.create('Ext.data.Store', {
	autoLoad: true,
	model: 'User',
	proxy: {
		type: 'ajax',
		url : 'users.json',
		reader: {
			type: 'json',
			root: 'users'
		}
	}
});

/*
This store will consume data of the following kind (assuming
thaat the 'User' model was correctly defined...)...
{
	'users': [
		{
			'id': 1,
			'name': 'Ed',
			'orders': [
				{
					id: 10,
					total: 10.76,
					status: 'invoiced'
				},
				{
					id: 11,
					total: 13.45,
					status: 'shipped'
				},
			]
		}
	]
}
*/

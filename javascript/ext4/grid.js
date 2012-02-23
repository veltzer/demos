Ext.onReady(function() {
	Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
	Ext.create('Ext.grid.Panel', {
		stateful: true,
		stateId: 'stateGrid',
		columns: [
			{
				text     : 'Company',
				flex     : 1,
				sortable : false,
				dataIndex: 'company'
			},
			{
				text     : 'Price',
				flex	: 1,
				sortable : true,
				renderer : 'usMoney',
				dataIndex: 'price'
			},
		],
		renderTo: Ext.getBody(),
	});
});

Ext.onReady(function() {
	Ext.define('MyPanel', {
		extend: 'Ext.Panel',
		layout: 'fit',
		initComponent: function() {
			this.callParent(arguments);
			var b_next=Ext.create('Ext.Button',{
				text: 'next',
				itemId: 'b_next',
				listeners: {
					click:function() {
						console.log('next');
					},
				},
			});
			var b_prev=Ext.create('Ext.Button',{
				text: 'prev',
				itemId: 'b_prev',
				listeners: {
					click:function() {
						console.log('prev');
					},
				},
			});
			this.add(b_prev);
			this.add(b_next);
		},
	});
	Ext.create('MyPanel',{
		renderTo: Ext.getBody()
	});
});

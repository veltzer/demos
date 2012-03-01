Ext.onReady(function() {
	Ext.define('MyPanel', {
		extend: 'Ext.Panel',
		layout: 'fit',
		initComponent: function() {
			this.callParent(arguments);
			var b_cancel=Ext.create('Ext.Button',{
				text: 'Cancel',
				itemId: 'b_cancel',
			});
			var b_ok=Ext.create('Ext.Button',{
				text: 'OK',
				itemId: 'b_ok',
				listeners: {
					click:function() {
						this.ownerCt.getComponent('b_cancel').setText('bye');
					},
				},
			});
			this.add(b_cancel);
			this.add(b_ok);
		},
	});
	Ext.create('MyPanel',{
		renderTo: Ext.getBody()
	});
});

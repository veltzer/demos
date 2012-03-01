Ext.onReady(function() {
	Ext.define('MyPanel', {
		extend: 'Ext.Panel',
		//layout: 'fit',
		//cls: 'myclass',
		layout: {
			type: 'hbox',
			//align: 'fit'
		},
		//width:250,
		//height:50,
		initComponent: function() {
			this.callParent(arguments);
			var w_minus=Ext.create('Ext.Button',{
				text: 'minus',
				itemId: 'w_minus',
				listeners: {
					click:function() {
						var current_value=this.ownerCt.getComponent('w_field').getValue();
						current_value-=0;
						current_value-=1;
						this.ownerCt.getComponent('w_field').setValue(current_value);
					},
				},
			});
			this.add(w_minus);
			var w_field=Ext.create('Ext.form.field.Text',{
				itemId: 'w_field',
				value: 0,
			});
			this.add(w_field);
			var w_plus=Ext.create('Ext.Button',{
				text: 'plus',
				itemId: 'w_plus',
				listeners: {
					click:function() {
						var current_value=this.ownerCt.getComponent('w_field').getValue();
						current_value-=0;
						current_value+=1;
						this.ownerCt.getComponent('w_field').setValue(current_value);
					},
				},
			});
			this.add(w_plus);
		},
	});
	Ext.create('MyPanel',{
		renderTo: Ext.getBody()
	});
});

Ext.onReady(function() {
	Ext.define('MyButton', {
		extend: 'Ext.Button',
		times:0,
		listeners: {
			click: function() {
				this.times+=1
				this.setText('I was clicked '+this.times+' times');
			},
		},
	});
	Ext.create('MyButton',{
		renderTo: Ext.getBody()
	});
});

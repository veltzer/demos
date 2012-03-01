Ext.define('Verint.MyButton', {
	extend: 'Ext.Button',
	times:0,
	listeners: {
		click: function() {
			this.times+=1
			this.setText('I was clicked '+this.times+' times');
		},
	},
});

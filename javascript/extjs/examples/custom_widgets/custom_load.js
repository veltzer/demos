Ext.Loader.setConfig({
	enabled: true
});
Ext.Loader.setPath('Verint','');
Ext.onReady(function() {
	Ext.create('Verint.MyButton',{
		renderTo: Ext.getBody()
	});
});

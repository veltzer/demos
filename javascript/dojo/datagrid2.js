dojo.require("dojox.grid.DataGrid");
dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dijit.form.Button");
function init() {
	// load some data
}
function do_click() {
	var w=dijit.byId("grid");
	var store=w.store;
	for(i=0;i<w.rowCount;i++) {
		var item=w.getItem(i);
		var old_price=store.getValue(item,'price');
		var new_price=old_price*1.15;
		store.setValue(item,'price',new_price);
	}
}
function do_populate() {
	// create some data
}
dojo.addOnLoad(init);

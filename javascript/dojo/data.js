dojo.require("dijit.form.Button");
dojo.require("dijit.form.TextBox");
dojo.require("dojo.data.ItemFileReadStore");

var startPoint = 0;
var store;
function initialization() {
	store = new dojo.data.ItemFileReadStore({url: "data.ajax"});
	getData();
}
function getData() {
	store.fetch({ start: startPoint , count: 3, onComplete: showCurrent})
}
dojo.addOnLoad(initialization);

function showCurrent(items,request) {
	if(items.length<3) {
		alert("no more data");
		dijit.byId("nextButton").attr('disabled',true);
		return;
	}
	if(items.length>=1) {
		var firstItem = items[0];
		dijit.byId("dataOne").attr('value',store.getValue(firstItem,'name'));
	}
	if(items.length>=2) {
		var secondItem = items[1];
		dijit.byId("dataTwo").attr('value',store.getValue(secondItem,'name'));
	}
	if(items.length>=3) {
		var thirdItem = items[2];
		dijit.byId("dataThree").attr('value',store.getValue(thirdItem,'name'));
	}
	if(items.length==3) {
		dijit.byId("nextButton").attr('disabled',false);
	}
}
function next() {
	// enable the prev button if we are right at the begining...
	if(startPoint==0)
		dijit.byId("prevButton").attr('disabled',false);
	startPoint+=3;
	getData();
}
function prev() {
	startPoint-=3;
	getData();
	if(startPoint==0)
		dijit.byId("prevButton").attr('disabled',true);
}

function Spinner(initialValue,id_for_append) {
	this.currentVal=initialValue;
	this.jq_increase=$('<button/>').text('+').addClass('increaseButton');
	this.jq_decrease=$('<button/>').text('-').addClass('decreaseButton');
	this.jq_text=$('<input/>').addClass('inputBox');
	$(id_for_append).append(this.jq_decrease);
	$(id_for_append).append(this.jq_text);
	$(id_for_append).append(this.jq_increase);
	// lets hook up methods...
	var widget=this;
	this.jq_increase.bind('click',function() {
		widget.onIncrease();
	});
	this.jq_decrease.bind('click',function() {
		widget.onDecrease();
	});
	this.updateValue();
}

Spinner.prototype.updateValue=function() {
	this.jq_text.val(this.currentVal);
}
// this method is called when the increase is clicked.
Spinner.prototype.onIncrease=function() {
	this.currentVal+=1;
	this.updateValue();
}
// this method is called when the decrease is clicked.
Spinner.prototype.onDecrease=function() {
	this.currentVal-=1;
	this.updateValue();
}

////////////////////////////////////////
// Here starts the SpinnerChild class //
////////////////////////////////////////
function SpinnerChild(initialValue,id_for_append) {
	Spinner.call(this,initialValue,id_for_append);
	// here you can put construction code specific to the child
	// class...
}
SpinnerChild.prototype=new Spinner(5,'#blabla');
// this method is called when the increase is clicked.
SpinnerChild.prototype.onIncrease=function() {
	this.currentVal+=1;
	Spinner.prototype.onIncrease.apply(this);
}
// this method is called when the decrease is clicked.
SpinnerChild.prototype.onDecrease=function() {
	this.currentVal-=1;
	Spinner.prototype.onDecrease.apply(this);
}

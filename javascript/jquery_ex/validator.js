function Validator(id_for_append,name,submitName,validator,errorMsg) {
	// this holds whether I am validated or not.
	// 0: not validated, 1: validated, -1: don't know
	this.state=-1;
	this.listeners=[];
	this.id_for_append=id_for_append;
	this.name=name;
	this.submitName=submitName;
	this.validator=validator;
	this.errorMsg=errorMsg;
	this.jq_span=$('<span/>').text(this.name).addClass('fieldTitle');
	this.jq_input=$('<input/>').addClass('validatorInputField');
	this.jq_err=$('<span/>').text(this.errorMsg).addClass('errorMessages');
	$(id_for_append).append(this.jq_span);
	$(id_for_append).append(this.jq_input);
	$(id_for_append).append(this.jq_err);
	// lets hook up methods...
	var widget=this;
	this.jq_input.bind('keyup',function() {
		widget.validate();
	});
	this.validate();
}

Validator.prototype.validate=function() {
	var result=this.validator(this.jq_input.val());
	if(result!=this.state) {
		if(result) {
			this.jq_err.hide();
		} else {
			this.jq_err.show();
		}
		this.state=result;
		this.notifyChanges(result);
	}
}
Validator.prototype.addListener=function(l) {
	this.listeners.push(l);
}
Validator.prototype.notifyChanges=function(data) {
	for(var i in this.listeners) {
		var elem=this.listeners[i];
		elem.notify(data);
	}
}
Validator.prototype.getSubmitName=function() {
	return this.submitName;
}
Validator.prototype.getValue=function() {
	return this.jq_input.val();
}

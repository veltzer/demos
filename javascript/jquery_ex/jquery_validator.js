function Validator(id_for_append,name,validator,errorMsg) {
	this.id_for_append=id_for_append;
	this.name=name;
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
	if(this.validator(this.jq_input.val())) {
		this.jq_err.hide();
	} else {
		this.jq_err.show();
	}
}

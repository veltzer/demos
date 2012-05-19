function AutoComplete(id_for_append,name,submitName,list,errorMsg) {
	this.id_for_append=id_for_append;
	this.name=name;
	this.submitName=submitName;
	this.list=list;
	this.errorMsg=errorMsg;
	this.div=$('<div/>');
	this.jq_span=$('<span/>').text(this.name).addClass('fieldTitle');
	this.jq_input=$('<input/>').addClass('autoCompleteInputField');
	this.jq_err=$('<span/>').text(this.errorMsg).addClass('errorMessages');
	this.div.append(this.jq_span);
	this.div.append(this.jq_input);
	this.div.append(this.jq_err);
	$(id_for_append).append(this.div);
	// lets hook up methods...
	var widget=this;
	this.jq_input.bind('keyup',function() {
		widget.complete();
	});
}

AutoComplete.prototype.complete=function() {
	var val=this.jq_input.val();
	//console.log('val is '+val);
	var scomplete=complete(val,this.list);
	//console.log('scomplete is '+scomplete);
	if(scomplete=='') {
		this.jq_err.show();
	} else {
		this.jq_err.hide();
		this.jq_input.val(scomplete);
	}
}
AutoComplete.prototype.getSubmitName=function() {
	return this.submitName;
}
AutoComplete.prototype.getValue=function() {
	return this.jq_input.val();
}

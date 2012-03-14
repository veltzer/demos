function Submit(id_for_append,mytext) {
	this.id_for_append=id_for_append;
	this.mytext=mytext;
	this.jq_button=$('<button/>').text(this.mytext).addClass('submitButton');
	$(id_for_append).append(this.jq_button);
	this.numberOfInvalid=0;
}

Submit.prototype.addField=function(field) {
	field.addListener(this);
}
Submit.prototype.notify=function(valid) {
	if(valid) {
		this.numberOfInvalid-=1;
	} else {
		this.numberOfInvalid+=1;
	}
	//console.log(this.numberOfInvalid);
	if(this.numberOfInvalid==0) {
		this.jq_button.attr('disabled',false);
	} else {
		this.jq_button.attr('disabled',true);
	}
}

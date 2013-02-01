function Submit(id_for_append,mytext) {
	this.id_for_append=id_for_append;
	this.mytext=mytext;
	this.jq_button=$('<button/>').text(this.mytext).addClass('submitButton');
	$(id_for_append).append(this.jq_button);
	this.numberOfInvalid=0;
	this.fields=[];
	var widget=this;
	this.jq_button.bind('click',function() {
		widget.click();
	});
}
Submit.prototype.click=function() {
	for(var i in this.fields) {
		var field=this.fields[i];
		field.animate();
	}
	var s='{';
	for(var i2 in this.fields) {
		var field2=this.fields[i2];
		//console.log(field.getSubmitName());
		//console.log(field.getValue());
		s+=field2.getSubmitName()+':"'+field2.getValue()+'",';//JSON
	}
	s+='}';
	var data={};
	data.payload=s;
	//console.log(s);
	$.ajax({
		type:'POST',
		url:'submit.php',
		dataType:'text',
		data: data,
		success:function(mydata,textStatus,XMLHttpRequest) {
			console.log('success data is ['+mydata+']',textStatus,XMLHttpRequest);
		},
		error:function(XMLHttpRequest,textStatus,errorThrown) {
			console.log('error',XMLHttpRequest,textStatus,errorThrown);
		}
	});
};
Submit.prototype.addField=function(field) {
	field.addListener(this);
	this.fields.push(field);
};
Submit.prototype.notify=function(valid) {
	if(valid) {
		this.numberOfInvalid-=1;
	} else {
		this.numberOfInvalid+=1;
	}
	//console.log(this.numberOfInvalid);
	if(this.numberOfInvalid===0) {
		this.jq_button.attr('disabled',false);
	} else {
		this.jq_button.attr('disabled',true);
	}
};

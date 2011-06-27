function PaginatedTable(options) {
	if(typeof(options.id)==='undefined') {
		throw String("must pass id");
	}
	if(typeof(options.dataurl)==='undefined') {
		throw String("must pass data url");
	}
	if(typeof(options.httpmethod)==='undefined') {
		throw String("must pass httpmethod");
	}
	this.debug_position=options.debug_position || 0;
	this.position=options.position || 0;
	this.dataurl=options.dataurl;
	this.httpmethod=options.httpmethod;
	this.rows=options.rows || 5;
	this.cols=options.cols || 5;
	this.create_buttons=options.create_buttons || 1;
	this.put_dummy_data=options.put_dummy_data || 0;
	this.id=options.id;
	this.tab=$('<table>');
	this.tab.addClass('PaginatedTable');
	this.data=new Array();
	for(i=0;i<this.rows;i++) {
		var tr=$('<tr>');
		tr.addClass('PaginatedRows');
		this.data[i]=new Array();
		for(j=0;j<this.cols;j++) {
			var td=$('<td>');
			td.addClass('PaginatedTableCells');
			if(i%2==0) {
				td.addClass('PaginatedTableCellsOdd');
			} else {
				td.addClass('PaginatedTableCellsEven');
			}
			if(this.put_dummy_data) {
				td.text(i+','+j);
			}
			this.data[i][j]=td;
			tr.append(td);
		}
		this.tab.append(tr);
	}
	if(this.create_buttons) {
		var prev=$('<button>');
		var next=$('<button>');
		prev.text('prev');
		next.text('next');
		var my_object=this;
		prev.click(function() {
			my_object.prev();
		});
		next.click(function() {
			my_object.next();
		});
	}
	if(this.debug_position) {
		this.d=$('<div>');
		$(this.id).append(this.d);
		this.updatePosition();
	}
	$(this.id).append(this.tab);
	if(this.create_buttons) {
		$(this.id).append(prev);
		$(this.id).append(next);
	}
	this.fetch();
	return this;
}
// bring over data via ajax...
PaginatedTable.prototype.populate=function(data, textStatus, jqXHR) {
	for(var i=0;i<this.getRows();i++) {
		for(var j=0;j<this.getCols();j++) {
			this.setData(i,j,data.data[i][j]);
		}
	}
}
PaginatedTable.prototype.error=function(jqXHR, textStatus, errorThrown) {
	alert('error in ajax request (either server did not respond, url not found, server error or parse error of the return value');
}
PaginatedTable.prototype.fetch=function() {
	// notice the use of the 'context' property that makes 'this' in the response
	// function be the PaginatedTable object itself...
	// the cache: false is not strictly needed since we are going to different urls because of
	// the 'data' that we pass. But in the debugging phase it is better to have that here...
	// In any case it could be that the server is not deterministic, meanining, that for the same
	// position in the table it sometimes returns different data...
	$.ajax({
		url: this.dataurl,
		context: this,
		cache: false,
		data: {
			position: this.position,
			rows: this.rows,
			cols: this.cols,
		},
		dataType: 'json',
		method: this.httpmethod,
		success: PaginatedTable.prototype.populate,
		error: PaginatedTable.prototype.error,
	});
}
PaginatedTable.prototype.updatePosition=function() {
	if(this.debug_position) {
		this.d.text(this.position);
	}
}
PaginatedTable.prototype.prev=function() {
	if(this.position>=this.rows) {
		this.position-=this.rows;
		this.updatePosition();
	}
	this.fetch();
}
PaginatedTable.prototype.next=function() {
	this.position+=this.rows;
	this.updatePosition();
	this.fetch();
}
PaginatedTable.prototype.getData=function(x,y) {
	return this.data[x][y].text();
}
PaginatedTable.prototype.setData=function(x,y,data) {
	this.data[x][y].text(data);
}
PaginatedTable.prototype.getCols=function() {
	return this.cols;
}
PaginatedTable.prototype.getRows=function() {
	return this.rows;
}

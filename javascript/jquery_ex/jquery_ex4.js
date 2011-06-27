// here starts the paginated table...
//
// TODO:
// - grey the 'prev' and 'next' buttons according to limits of data.
// - don't do 'alert' on errors in ajax and instead show the errors in some
// nice place on the screen or in the console or both.
function PaginatedTable(options) {
	/*
	if('id' in options) {
		throw String("must pass id");
	}
	if('dataurl' in options) {
		throw String("must pass data url");
	}
	*/
	this.httpmethod=options.httpmethod || "GET";
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
	for(var i=0;i<this.rows;i++) {
		var tr=$('<tr>');
		tr.attr('rowNumber',i);
		tr.click(function() {
			PostOffice.getInstance().publish('/rowClicked',[$(this).attr('rowNumber')]);
		});
		tr.addClass('PaginatedRows');
		this.data[i]=new Array();
		for(var j=0;j<this.cols;j++) {
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
			td.attr('rowNumber',i);
			td.attr('colNumber',j);
			td.click(function() {
				PostOffice.getInstance().publish('/cellClicked',[$(this).attr('rowNumber'),$(this).attr('colNumber')]);
			});
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
	// lets subscribe to outside 'next' and 'prev' requests...
	PostOffice.getInstance().subscribe('/next',this,'next');
	PostOffice.getInstance().subscribe('/prev',this,'prev');
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
	// there is no need for the 'cache' thing if the server is configured properly...
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
		this.fetch();
	}
	PostOffice.getInstance().publish('/doingPrev',[]);
}
PaginatedTable.prototype.next=function() {
	this.position+=this.rows;
	this.updatePosition();
	this.fetch();
	PostOffice.getInstance().publish('/doingNext',[]);
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

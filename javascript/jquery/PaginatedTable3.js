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
	this.debug_position=options.debug_position || 1;
	this.position=options.position || 0;
	this.dataurl=options.dataurl;
	this.httpmethod=options.httpmethod;
	this.rows=options.rows || 5;
	this.cols=options.cols || 5;
	this.create_buttons=options.create_buttons || 1;
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
				td.addClass('odd_cells');
			} else {
				td.addClass('even_cells');
			}
			//td.text(i+','+j);
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
PaginatedTable.prototype.populate=function(data) {
	console.log(data);
	//alert(this.getCols());
	for(var i=0;i<this.getRows();i++) {
		for(var j=0;j<this.getCols();j++) {
			//alert('i is '+i+' j is '+j+' and data is '+data.data[i][j]);
			//this.setData(i,j,data.data[i][j]);
			//this.setData(i,j,Math.random());
		}
	}
}
PaginatedTable.prototype.fetch=function() {
	//alert(this.dataurl);
	$.ajax({
		url: this.dataurl,
		context: this,
		data: {
			position: this.position,
			rows: this.rows,
		},
		datatype: 'json',
		method: this.httpmethod,
		success: function(data) {
			// now I can access the widget via 'widget'
			this.populate(data);
		},
		error: function() {
			// now I can access the widget via 'widget'
			alert('got bad data from the server');
		},
	});
}
PaginatedTable.prototype.updatePosition=function() {
	this.d.text(this.position);
}
PaginatedTable.prototype.prev=function() {
	if(this.position>=this.rows) {
		this.position-=this.rows;
		this.updatePosition();
	}
}
PaginatedTable.prototype.next=function() {
	this.position+=this.rows;
	this.updatePosition();
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

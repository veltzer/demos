function PaginatedTable(options) {
	if(typeof(options.id)==='undefined') {
		throw String("must pass id");
	}
	this.rows=options.rows || 5;
	this.cols=options.cols || 5;
	this.create_buttons=options.create_buttons || 1;
	this.id=options.id;
	this.tab=$('<table>').addClass('PaginatedTable');
	this.data=new Array();
	for(var i=0;i<this.rows;i++) {
		var tr=$('<tr>').addClass('PaginatedRows');
		this.data[i]=new Array();
		for(var j=0;j<this.cols;j++) {
			var td=$('<td>').addClass('PaginatedTableCells');
			if(i%2==0) {
				td.addClass('PaginatedTableCellsEven');
			} else {
				td.addClass('PaginatedTableCellsOdd');
			}
			// just for debug...
			td.text(i+','+j);
			this.data[i][j]=td;
			tr.append(td);
		}
		this.tab.append(tr);
	}
	$(this.id).append(this.tab);
	if(this.create_buttons) {
		var prev=$('<button>').text('prev');
		var next=$('<button>').text('next');
		var widget=this;
		prev.click(function() {
			widget.prev();
		});
		next.click(function() {
			widget.next();
		});
		$(this.id).append(prev);
		$(this.id).append(next);
	}
	return this;
}
PaginatedTable.prototype.prev=function() {
	// STILL TODO
	console.log('prev');
}
PaginatedTable.prototype.next=function() {
	// STILL TODO
	console.log('next');
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

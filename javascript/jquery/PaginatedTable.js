function PaginatedTable(options) {
	if(typeof(options.id)==='undefined') {
		throw String("must pass id");
	}
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
			td.text(i+','+j);
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
	$(this.id).append(this.tab);
	$(this.id).append(prev);
	$(this.id).append(next);
	return this;
}
PaginatedTable.prototype.prev=function() {
	// TODO
	alert('prev');
}
PaginatedTable.prototype.next=function() {
	// TODO
	alert('next');
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

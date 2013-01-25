function HorVertElem(elid) {
	this.el=$(elid);
}

/*
 * A layout manager that prefers horizontal to vertical when ever possible
 */

function HorVert(id,margin) {
	this.total=$(id);
	this.stopResize=true;
	//this.stopResize=false;
	this.doDebug=true;
	this.margin=margin;
	this.elems=[];
	// for closure
	var widget=this;
	$(window).resize(function() {
		widget.resize();
	});
}

HorVert.prototype.debug=function(msg) {
	if(this.doDebug) {
		console.log(msg);
	}
};

HorVert.prototype.addElem=function(elem) {
	this.elems.push(elem);
	elem.el.appendTo(this.total);
	this.resize();
};

HorVert.prototype.stopResize=function() {
	this.stopResize=true;
};

HorVert.prototype.startResize=function() {
	this.stopResize=false;
	this.resize();
};

HorVert.prototype.resize=function() {
	if(this.stopResize) {
		return;
	}
	//var total_width=this.total.width();
	var total_width=$(window).width();
	this.debug('total_width='+total_width);
	var runner_x=this.margin;
	var runner_y=this.margin;
	var max_vert_size=0;
	for(var i in this.elems) {
		var elem=this.elems[i];
		// if we don't have enough horizontal space...
		this.debug("elem.el.width is "+elem.el.width());
		if(runner_x+elem.el.width()>total_width) {
			// need to go down...
			runner_x=this.margin;
			runner_y+=max_vert_size+this.margin;
			max_vert_size=0;
		} else {
			this.debug("putting in "+runner_x+","+runner_y);
			elem.el.offset({left:runner_x,top:runner_y});
			if(max_vert_size<elem.el.height()) {
				max_vert_size=elem.el.height();
			}
			runner_x+=elem.el.width()+this.margin;
		}
	}
};

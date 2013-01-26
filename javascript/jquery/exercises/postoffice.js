/*
Here comes the post office implementation...

TODO:
- add a fixed list of events and only allow publish/subscribe on those.
- add generic debug infrastructure.
- throw better exceptions.
- hide the singleton instance via closures.
- add different payload types (more than one ?!?)
- decouple the publishing and subscribing contexts.
- more ?!?
*/
function PostOffice() {
	if(PostOffice.instance) {
		throw String('contructing a postoffice twice ?!?');
	} else {
		this.subscribers={};
		PostOffice.instance=this;
	}
}
PostOffice.instance=undefined;
PostOffice.getInstance=function() {
	if(PostOffice.instance==undefined) {
		PostOffice.instance=new PostOffice();
	}
	return PostOffice.instance;
};
PostOffice.prototype.subscribe=function(evt,obj,method) {
	if(!this.subscribers[evt]) {
		this.subscribers[evt]=[];
	}
	this.subscribers[evt].push([obj,method]);
};
PostOffice.prototype.publish=function(evt,data) {
	// We do stuff only if there are subscribers...
	if(this.subscribers[evt]) {
		for(var x in this.subscribers[evt]) {
			var oam=this.subscribers[evt][x];
			oam[0][oam[1]](data);
		}
	}
};

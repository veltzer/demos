var first=true;
var id=undefined;
// this next line causes an exception even though chrome has a console tab
// in it's debugger...
//console.log('hello');
function myonmessage(e) {
	if(first) {
		first=false;
		id=e.data;
		//self.postMessage('hi');
		//self.postMessage('worker got id '+id);
		self.postMessage('worker got id '+id);
		return;
	}
	if(e.data=='close') {
		postMessage(id+' calling close()');
		// this is the best way for a webworker to finish up
		close();
		return;
	}
	if(e.data=='undefine') {
		postMessage(id+' unregistering onmessage');
		onmessage=undefined;
		//delete window.onmessage;
		return;
	}
	if(e.data=='error') {
		throw 'this is an error from worker '+id;
		var d=t+u;
		return;
	}
	if(e.data=='debug') {
		postMessage(''+self);
		postMessage(''+location);
		for(var k in self) {
			postMessage(id+': '+self[k]);
		}
		return;
	}
	// this is for regular messages that don't have a special meaning
	postMessage(id+' got '+e.data);
}
function myonerror() {
	postMessage(id+' onerror');
}
function myonclose() {
	postMessage(id+' onclose');
}
postMessage('anonymous worker starting...');
self.onmessage=myonmessage;
self.onerror=myonerror;
self.onclose=myonclose;

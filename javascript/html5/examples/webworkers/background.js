var first=true;
var id=undefined;
function myonmessage(e) {
	if(first) {
		first=false;
		id=e.data;
		postMessage('worker got id '+e.data);
		return;
	}
	if(e.data=='close') {
		postMessage(id+' calling close()');
		// this is the best way for a webworker to finish up
		close();
		return;
	}
	if(e.data=='undefine') {
		postMessage(id+' doing onmessage=undefined');
		onmessage=undefined;
		//delete window.onmessage;
		return;
	}
	if(e.data=='error') {
		throw 'this is an error from worker '+id;
		var d=t+u;
		return;
	}
	postMessage(id+' got '+e.data);
}
function myonclose() {
	postMessage(id+' onclose');
}
function myonerror() {
	postMessage(id+' onerror');
}

postMessage('anonymous worker starting...');
/*
postMessage(self);
postMessage(location);
for(var id in self) {
	postMessage(id+': '+self[id]);
}
*/
self.onmessage=myonmessage;
self.onclose=myonclose;
self.onerror=myonerror;

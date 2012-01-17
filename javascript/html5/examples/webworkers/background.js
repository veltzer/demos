var first=true;
var id=undefined;
function myonmessage(e) {
	if(first) {
		first=false;
		id=e.data;
		postMessage('worker got id '+e.data);
		return;
	}
	if(e.data=='exit') {
		postMessage(id+' dying');
		// these are the ways the worker may kill itself
		//close();
		onmessage=undefined;
		//delete window.onmessage;
	} else {
		postMessage(id+' got '+e.data);
	}
}
function myonclose(e) {
	postMessage(id+' onclose');
}

postMessage('anonymous worker starting...');
onmessage=myonmessage;
onclose=myonclose;

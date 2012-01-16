var first=true;
var id=undefined;
function myonmessage(e) {
	if(first) {
		first=false;
		id=e.data;
		postMessage('worker got id '+e.data);
	} else {
		postMessage(id+' got '+e.data);
	}
}

function trigger() {
}

postMessage('anonymous worker starting...');
onmessage=myonmessage;
timer=setTimeout(trigger,2000);

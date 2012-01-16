var first=true;
var id=undefined;
function myonmessage(e) {
	if(first) {
		first=false;
		id=e.data;
	} else {
		postMessage(id+' got '+e.data);
	}
}

function trigger() {
}

postMessage('starting...');
onmessage=myonmessage;
timer=setTimeout(trigger,2000);

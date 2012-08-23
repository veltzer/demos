var timer;

onload = init;
onmessage = messageHandler;
onerror = errorHandler;

function messageHandler(event) {
	var embeddedMessage;
	if (event.data.action) {
		embeddedMessage = event.data.action;
		if (event.data.action == "resume") {
			trigger();
		} else if (event.data.action == "pause"){
			clearTimeout(timer);
		}
	} else if (event.data.message) {
		embeddedMessage = event.data.message;
	} else {
		embeddedMessage = event.data;
	}
	
	postMessage( {
		message : "I have received an event: " + embeddedMessage,
		timestamp : new Date()
	});
}

function errorHandler(event) {
	postMessage( {
		message : "I have received an error event: " + event.data,
		timestamp : new Date()
	});
}

function init() {
	trigger();
	postMessage( {
		message : "Init",
		timestamp : new Date()
	});
}

function trigger() {
	postMessage( {
		message : "Data Update",
		timestamp : new Date(),
		results: [
		          Math.floor(Math.random() * 351),
		          Math.floor(Math.random() * 351),
		          Math.floor(Math.random() * 351),
		          Math.floor(Math.random() * 351),
		          Math.floor(Math.random() * 351),
		          Math.floor(Math.random() * 351)
		          ]
	});
	timer = setTimeout(trigger, 2000);
}

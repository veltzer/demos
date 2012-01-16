
function messageHandler(event) {
}

function errorHandler(event) {
}

function updateData(data) {
	postMessage(data);
}

function init() {
	trigger();
	updateData( {
		message : "Init",
		timestamp : new Date()
	});
}

function trigger() {
}

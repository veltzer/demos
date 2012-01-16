
function init() {
}


function processSubmit() {
}

function messageHandler(event) {
}

function errorHandler(event) {
}

function drawResults(resultsArray) {
	var colors = ["#ff0000", "#00ff00", "#ffff00", "#ff00ff", "#00ffff", "#0000ff", "#f0f0f0", "#f0000f", "#f0f000", "#00ff0f"];
	drawGrid(mapCanvas, 20);
	var context = mapCanvas.getContext("2d");
	for (var i = 0; i < resultsArray.length; i++) {
		drawBar(context, (i*40+30), resultsArray[i], colors[i]);
	}
}

function drawBar(context, relativeX, height, style) {
	var padding = 20;
	var bottomLine = mapCanvas.height;
	context.clearRect(padding + relativeX - 10, bottomLine, padding + relativeX + 10, bottomLine - height);
	
	// Draw new bar
	context.beginPath();
	context.moveTo(padding + relativeX - 10, bottomLine);
	context.lineTo(padding + relativeX + 10, bottomLine);
	context.lineTo(padding + relativeX + 10, bottomLine - height);
	context.lineTo(padding + relativeX - 10, bottomLine - height);
	context.closePath();
	context.fillStyle=style;
	context.fill();
}

function drawGrid(canvas, gridSize) {
	var context = canvas.getContext("2d");
	context.clearRect(0, 0, canvas.width-1, canvas.height-1);
	context.lineWidth=1;
	for (var i = 0 ; i < canvas.width; i+=gridSize) {
		context.beginPath();
		context.moveTo(i, 0);
		context.lineTo(i, canvas.height);
		context.stroke();
	}
	context.moveTo(canvas.width, 0);
	context.lineTo(canvas.width, canvas.height);
	context.stroke();
	for (var i = 0 ; i < canvas.height; i+=gridSize) {
		context.beginPath();
		context.moveTo(0, i);
		context.lineTo(canvas.width, i);
		context.stroke();
	}
	context.moveTo(0, canvas.height);
	context.lineTo(canvas.width, canvas.height);
	context.stroke();
}

function getBrowserInfo() {
	var browserInfo = new Object();
	var agent = navigator.userAgent;
	if (agent.match(/Chrome/)) {
		browserInfo.product = "Chrome";
		browserInfo.version = agent.replace(/^(.*) Chrome\/(.*) (.*)/, "$2");
	} else if (agent.match(/Opera/)) {
		browserInfo.product = "Opera";
		browserInfo.version = agent.replace(/^(.*) Version\/(.*)/, "$2");
	} else {
		agent = agent.replace(/^(.*) (.*)$/, "$2");
		browserInfo.product = agent.replace(/^(.*)\/(.*)$/, "$1");
		browserInfo.version = agent.replace(/^(.*)\/(.*)$/, "$2");
	}
	return browserInfo;
}

function setStatus(status) {
	divStatus.innerHTML = status;
}

function addStatus(status) {
	divStatus.innerHTML += "<br/>" + status;
}

window.onload = init;


